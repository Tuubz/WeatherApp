package app.krungsri.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather(@SerializedName("coord") val coordinates: Coordinates, @SerializedName("main") val metrics: Metrics, @SerializedName("weather") val type: List<Type>)

