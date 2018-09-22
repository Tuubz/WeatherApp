package app.krungsri.weatherapp.viewmodel

import android.arch.lifecycle.ViewModel
import app.krungsri.weatherapp.service.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getCurrentWeather(city: String, units: String) {

    }
}