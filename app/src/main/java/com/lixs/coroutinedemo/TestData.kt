package com.lixs.coroutinedemo

import com.google.gson.annotations.SerializedName

data class Result(@SerializedName("status") val status: Int) {}

data class Weather(@SerializedName("daily_forecast") val forecast: List<Forecast>){}

data class Forecast(val date: String, @SerializedName("cond") val more: More, @SerializedName("tmp") val temperature: Temperature) {}

data class Temperature(val max: String, val min: String) {}

data class More(@SerializedName("txt_d") val info: String) {}
