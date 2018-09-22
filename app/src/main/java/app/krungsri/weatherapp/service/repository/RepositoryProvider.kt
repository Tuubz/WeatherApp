package app.krungsri.weatherapp.service.repository

import app.krungsri.weatherapp.service.WeatherApiService

object RepositoryProvider {
    fun provideRepository(): WeatherRepository {
        return WeatherRepository(WeatherApiService.create())
    }
}