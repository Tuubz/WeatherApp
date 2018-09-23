package app.krungsri.weatherapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.service.repository.RepositoryProvider
import app.krungsri.weatherapp.service.repository.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

class WeatherViewModel() : ViewModel() {

    private val weatherRepository = RepositoryProvider.provideRepository()

    fun getCurrentWeather(city: String, units: String) {
        weatherRepository.getCurrentWeather(city, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    Log.d("FCK", "$result")
                }, { error ->
                    Log.d("FCK", "$error")

                })
    }
}