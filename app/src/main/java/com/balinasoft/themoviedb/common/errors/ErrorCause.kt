package com.balinasoft.themoviedb.common.errors

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.MvpViewState
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.di.DaggerUtils
import com.google.gson.Gson
import com.vmeste.app.common.errors.excetpions.ServiceException
import com.vmeste.app.common.errors.excetpions.WrongFieldException
import org.jetbrains.anko.toast
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

class BodyError(val message: String)

class ErrorCause : Exception {

    var messageResId: Int? = null
        private set

    var httpCode: Int? = null
        private set

    var isConnectException = false
        private set

    var params: List<Any>? = null
        private set

    var error: ErrorPreset? = null
        private set

    enum class ErrorPreset() {
        ERROR_UNAUTHORIZED,
        ERROR_FORBIDDEN,
        ERROR_SILENT
    }

    companion object {

        fun createErrorCause(e: Throwable): ErrorCause = when (e) {
            is ErrorCause -> e
            is HttpException -> HttpErrorResponseParser().parse(e)
            is SocketTimeoutException -> ErrorCause(R.string.error_unknown_host_exception).also {
                it.isConnectException = true
            }
            is UnknownHostException -> ErrorCause(R.string.error_unknown_host_exception).also {
                it.isConnectException = true
            }
            is ConnectException -> ErrorCause(R.string.error_unknown_host_exception).also {
                it.isConnectException = true
            }
            else -> ErrorCause(R.string.error_unknown)
        }

    }

    constructor(errorPreset: ErrorPreset) {
        this.error = errorPreset
    }

    constructor(message: String) : super(message)

    constructor(message: String, httpException: HttpException) : super(message, httpException) {
        this.httpCode = httpException.code()
    }

    constructor(httpException: HttpException) : super(httpException) {
        this.httpCode = httpException.code()
    }


    constructor(@StringRes messageResId: Int) {
        this.messageResId = messageResId
    }

    constructor(@StringRes messageResId: Int, list: List<Any>) {
        this.messageResId = messageResId
        this.params = list
    }

    fun getErrorCause(context: Context): String {
        val errorMessage = message

        if (errorMessage != null) {
            return errorMessage
        }

        val params = this.params

        if (params != null) {

            return String.format(context.getString(messageResId!!), *params.toTypedArray())

        } else {
            messageResId?.let {
                return context.getString(it)
            }
        }
        return context.getString(R.string.error_unknown)
    }

}

@StateStrategyType(OneExecutionStateStrategy::class)
fun MvpView.showMessage(errorCause: ErrorCause, reloadable: ((String) -> Unit)? = null) {

    if (errorCause.error == ErrorCause.ErrorPreset.ERROR_SILENT) {
        return
    }

    val view = if (this is MvpViewState<*>) {
        (this as MvpViewState<*>).views.find { it is Context || it is Fragment }
    } else {
        null
    }


    val context: Context? = view?.getContext() ?: this.getContext()

    if (errorCause.error == ErrorCause.ErrorPreset.ERROR_UNAUTHORIZED) {
        context?.let { c ->
            /* DaggerUtils.appComponent.providePreferencesRepository().clearPrefs()
             val intent = Intent(c, RootActivity::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
             c.startActivity(intent)*/
        }
        return
    }

    context?.let {

        val message = errorCause.getErrorCause(it)

        if (reloadable != null) {
            reloadable.invoke(message)
        } else {
            context.toast(message)
        }
    }
}

fun MvpView.getContext(): Context? {
    return when (this) {
        is Activity -> this
        is Fragment -> this.activity
        else -> null
    }
}

fun <T : View> MvpView.getLayout(id: Int): T? {
    return when (this) {
        is Activity -> findViewById(id)
        is Fragment -> view?.findViewById(id)
        else -> null
    }
}

@StateStrategyType(OneExecutionStateStrategy::class)
fun MvpView.showMessage(e: Throwable, reloadable: ((String) -> Unit)? = null) {

    e.printStackTrace()


    val errorMessage = when (e) {
        is ErrorCause -> e
        is HttpException -> {
            when (e.code()) {
                401 -> ErrorCause(ErrorCause.ErrorPreset.ERROR_UNAUTHORIZED)
                else -> {
                    val default = DaggerUtils.appComponent.provideStringProvider()
                        .getString(R.string.unknown_http_exception, e.code().toString())

                    ErrorCause(e.parse().message ?: default)
                }
            }
        }
        is SocketTimeoutException -> ErrorCause(R.string.error_socket_timeout_exception)
        is UnknownHostException -> ErrorCause(R.string.error_unknown_host_exception)
        is ConnectException -> ErrorCause(R.string.error_unknown_host_exception)
        is WrongFieldException -> {
            val message = e.messageString
            if (message != null) {
                ErrorCause(message)
            } else {
                if (e.resourceId != null) {
                    if (e.additionalData == null) {
                        ErrorCause(e.resourceId!!)
                    } else {
                        ErrorCause(e.resourceId!!, e.additionalData!!)
                    }
                } else {
                    ErrorCause(R.string.error_unknown)
                }
            }
        }
        is ServiceException -> ErrorCause(e.messageId)
        is CancellationException -> {
            return
        }
        else -> {
            val s = e.localizedMessage ?: e.message
            if (s != null) {
                ErrorCause(s)
            } else {
                ErrorCause(R.string.error_unknown)
            }
        }
    }

    this.showMessage(errorMessage, reloadable)
}

fun HttpException.parse(): Exception {
    return try {
        Exception(
            Gson().fromJson(
                this.response().errorBody()?.string(),
                BodyError::class.java
            ).message, this.cause
        )
    } catch (e: Exception) {
        Exception("Unknown errror")
    }
}
