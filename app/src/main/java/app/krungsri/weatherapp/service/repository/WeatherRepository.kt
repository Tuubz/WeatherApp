package app.krungsri.weatherapp.service.repository

import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.service.WeatherApiService
import io.reactivex.Observable

class WeatherRepository(private val apiService: WeatherApiService) {

    fun getCurrentWeather(city: String, units: String, appid: String): Observable<Weather> {
        return apiService.getCurrentWeather(city, units, appid)
    }

}