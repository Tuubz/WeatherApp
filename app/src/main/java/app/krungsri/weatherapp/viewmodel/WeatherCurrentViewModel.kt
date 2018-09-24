package app.krungsri.weatherapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import app.krungsri.weatherapp.App
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.service.repository.RepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherCurrentViewModel : ViewModel() {

    private val weatherRepository = RepositoryProvider.provideRepository()
    private val _weather = MutableLiveData<Weather>()
    val weather : LiveData<Weather>
        get() = _weather

    fun getCurrentWeather(city: String, units: String) {
        weatherRepository.getCurrentWeather(city, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    _weather.value = Weather(result.metrics, result.type, result.date)
                }, { error ->
                    Toast.makeText(App.applicationContext(), error.message, Toast.LENGTH_SHORT).show()
                })
    }
}