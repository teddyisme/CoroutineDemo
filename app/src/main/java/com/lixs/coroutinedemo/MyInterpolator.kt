package com.lixs.coroutinedemo

import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator

class MyInterpolator : AccelerateDecelerateInterpolator() {
    var start = 0L
    override fun getInterpolation(input: Float): Float {
        if (input == 0f) {
            start = System.currentTimeMillis()
            Log.d("PPPP", start.toString())
        }
        val current = System.currentTimeMillis()

        if (input == 0.5f && (current - start) / 1000 < 400) {
            Log.d("PPPP", "----.")
            return 0.5f
        }
        return super.getInterpolation(input)
    }
}