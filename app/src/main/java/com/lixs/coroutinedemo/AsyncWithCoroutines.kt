package com.lixs.coroutinedemo

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


internal class CoroutineLifecycleListener(private val deferred: Deferred<*>) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelCoroutine() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}

private val Background = newFixedThreadPoolContext(1, "bg")
private val UI: CoroutineContext = Dispatchers.Main

fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
    val deferred = GlobalScope.async(context = Background, start = CoroutineStart.LAZY) {
        loader()
    }
    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}

infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
    return GlobalScope.launch(UI) {
        try {
            block(this@then.await())
        } catch (e: Exception) {
            throw e
        }
    }
}

fun <T> AppCompatActivity.http(
    dataClass: Class<T>,
    param: HashMap<String, String>,
    succse: suspend (d: T) -> Unit,
    failed: suspend () -> Unit
) {
    var data: T? = null
    load {
        //        Weather::class.javaObjectType
        try {
            Thread.sleep(2000)
            data = Gson().fromJson(testData, dataClass)
        } catch (e: java.lang.Exception) {

        }
    } then {
        if (data != null) {
            succse(data!!)
        } else {
            failed()
        }
    }
}

val testData = """{
    "daily_forecast": [
        {
            "date": "2017-7-25",
            "cond": {
                "txt_d": "阵雨"
            },
            "tmp": {
                "max": "34",
                "min": "27"
            }
        },
        {
            "date": "2017-7-26",
            "cond": {
                "txt_d": "晴"
            },
            "tmp": {
                "max": "38",
                "min": "27"
            }
        }
    ]
}
"""