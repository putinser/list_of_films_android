package com.balinasoft.themoviedb.common.errors

import com.google.gson.Gson
import me.codezfox.errors.Error
import me.codezfox.errors.ErrorResponse
import retrofit2.HttpException

class HttpErrorResponseParser {

    fun parse(httpException: HttpException): ErrorCause {
        try {
            val errorBody = httpException.response().errorBody()!!
            val errorResponse = Gson().fromJson(errorBody.string(), ErrorResponse::class.java)
            return if (errorResponse != null) {
                ErrorCause(
                    Gson().fromJson(errorResponse.message, Error::class.java).message,
                    httpException
                )
            } else {
                ErrorCause(httpException)
            }
        } catch (e: Exception) {
            e.printStackTrace()
//            return ErrorCause(R.string.error_unknown)
            return ErrorCause(httpException.localizedMessage, httpException)

        }
    }
}