package app.krungsri.weatherapp.service.repository

import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.service.WeatherApiService
import io.reactivex.Observable

class Repository(private val apiService: WeatherApiService) {

    fun getCurrentWeather(city: String, units: String): Observable<Weather> {
        return apiService.getCurrentWeather(city, units)
    }

}