package com.lixs.coroutinedemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {

        }

        repeat(10) {
            //            load {
//                Thread.sleep(1000)
//                it
//            } then {
//                testTv.text = "第${it + 1}次"
//            }

//            GlobalScope.launch(Background) {
//                Thread.sleep(1000)
//                Log.d("PPPP", "---> ${it + 1}")
//                GlobalScope.launch(UI) {
//                    testTv.text = "第${it + 1}次"
//                }
//            }
        }

        http(Weather::class.java, hashMapOf(), {
            testTv.text = it.forecast[0].date
        }, {
            testTv.text = "failed"
        })

        Thread(Runnable {
            Looper.prepare()
            Handler {
                true
            }
            Looper.loop()
        })
    }
}
