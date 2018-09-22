package app.weather.krungsi.weatherapp.service

import app.weather.krungsi.weatherapp.model.Weather
import io.reactivex.Observable
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET("weather")
    fun getCurrentWeather(@Query("q") city: String, @Query("units") units: String): Observable<Weather>

    companion object Factory {
        private const val BASE_URL = "api.openweathermap.org/data/2.5/"
        private val dispatcher = Dispatcher()

        fun create(): WeatherApiService {
            val okHttpClient = OkHttpClient.Builder()
            dispatcher.maxRequests = 1
            val retrofit = Retrofit.Builder()
                    .client(okHttpClient.dispatcher(dispatcher).build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(WeatherApiService::class.java)
        }

        fun cancelRequests(){
            dispatcher.cancelAll()
        }
    }
}