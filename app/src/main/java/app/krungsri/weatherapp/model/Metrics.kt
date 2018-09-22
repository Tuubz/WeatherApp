package app.krungsri.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Metrics(@SerializedName("temp") val temperature: Float, val humidity: Float)
