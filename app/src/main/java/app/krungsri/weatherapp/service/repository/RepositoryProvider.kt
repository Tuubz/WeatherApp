package app.krungsri.weatherapp.service.repository

import app.krungsri.weatherapp.service.WeatherApiService

object RepositoryProvider {
    fun provideRepository(): Repository {
        return Repository(WeatherApiService.create())
    }
}