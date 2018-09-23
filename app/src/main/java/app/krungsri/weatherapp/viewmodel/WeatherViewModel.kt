package app.krungsri.weatherapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.service.repository.RepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

class WeatherViewModel : ViewModel() {

    private val weatherRepository = RepositoryProvider.provideRepository()
    private val _weather = MutableLiveData<Weather>()
    val weather : LiveData<Weather>
        get() = _weather

    fun getCurrentWeather(city: String, units: String) {
        weatherRepository.getCurrentWeather(city, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    _weather.value = Weather(result.metrics, result.type)
                }, { error ->

                })
    }
}