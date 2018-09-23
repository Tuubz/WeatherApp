package app.krungsri.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list") val weathers: ArrayList<Weather>)

