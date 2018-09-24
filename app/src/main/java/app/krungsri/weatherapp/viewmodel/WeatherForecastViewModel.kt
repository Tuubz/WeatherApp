package app.krungsri.weatherapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import app.krungsri.weatherapp.App
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.model.WeatherList
import app.krungsri.weatherapp.service.repository.RepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherForecastViewModel : ViewModel() {

    private val weatherRepository = RepositoryProvider.provideRepository()
    private val _weathers = MutableLiveData<WeatherList>()
    val weathers : LiveData<WeatherList>
        get() = _weathers

    fun getForecastWeather(city: String, units: String, count: Int) {
        weatherRepository.getForecastWeather(city, units, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    _weathers.value = result
                }, { error ->
                    Toast.makeText(App.applicationContext(), error.message, Toast.LENGTH_SHORT).show()
                })
    }
}