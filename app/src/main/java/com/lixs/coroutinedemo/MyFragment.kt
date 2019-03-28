package com.lixs.coroutinedemo

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MyFragment : Fragment() {

    fun test() {
        http(Weather::class.java, hashMapOf(), {
            testTv.text = it.forecast[0].date
        }, {
            testTv.text = "failed"
        })
    }
}