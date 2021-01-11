package com.vmeste.app.common.extension

import com.balinasoft.themoviedb.common.extension.CallBackK
import com.github.kittinunf.result.Result
import kotlinx.coroutines.*

typealias ResultDefault<K> = Result<K, Exception>
typealias Response<K> = Deferred<ResultDefault<K>>

class NullableWrapper<T>(val value: T?)

fun launchUI(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return GlobalScope.launch(Dispatchers.Main, start, block)
}

inline fun <T : Any> asyncR(crossinline callback: () -> T): Response<T> {

    return GlobalScope.async {
        Result.of<T, java.lang.Exception> {
            callback.invoke()
        }
    }

}


fun <T : Any> asyncRWithScope(block: suspend CoroutineScope.() -> T): Response<T> {

    return GlobalScope.async {
        try {
            val res = block()
            Result.Success<T, java.lang.Exception>(res)
        } catch (e: java.lang.Exception) {
            Result.Failure<T, java.lang.Exception>(e)
        }
    }

}

//
//
//inline fun <V : Any> (() -> V).asyncUI(crossinline callback: () -> V, crossinline success: Callback<V>, crossinline failure: Callback<Exception>): Job {
//    return launch(UI) {
//        async {
//            this.invoke()
//        }.awaitFold(success, failure)
//    }
//}


suspend inline fun <V : Any> Response<V>.awaitFold(
    success: CallBackK<V>,
    failure: CallBackK<Exception>
) {
    return this.await().fold(success, failure)
}

suspend fun <V : Any> Response<V>.awaitOrCrush(): V {
    return GlobalScope.async {
        val i = await()
        i.component2()?.let {
            throw it
        }
        i.component1()!!

    }.await()
}

