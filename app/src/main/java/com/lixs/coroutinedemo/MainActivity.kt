package com.lixs.coroutinedemo

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
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


        val typeface = Typeface.createFromAsset(assets, "DIN-BoldAlternate.ttf")
        testTv.typeface = typeface


        http(Weather::class.java, hashMapOf(), {
            testTv.text = it.forecast[0].date
        }, {
            testTv.text = "failed"
        })

        val rotate = RotateAnimation(-15f, 15f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.2f)
        val lin = AccelerateDecelerateInterpolator()
        rotate.interpolator = lin
        rotate.duration = 300
        rotate.repeatCount = -1
        rotate.fillAfter = true
        rotate.repeatMode = Animation.REVERSE
        lingdang.startAnimation(rotate)

        Thread(Runnable {
            Looper.prepare()
            Handler {
                true
            }
            Looper.loop()
        })
    }
}
