package app.krungsri.weatherapp.service

import app.krungsri.weatherapp.App
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.model.WeatherList
import app.weather.krungsi.weatherapp.R
import io.reactivex.Observable
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET("weather")
    fun getCurrentWeather(@Query("q") city: String, @Query("units") units: String): Observable<Weather>

    @GET("forecast")
    fun getForecastWeather(@Query("q") city: String, @Query("units") units: String, @Query("cnt") count: Int): Observable<WeatherList>

    companion object Factory {
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        private val dispatcher = Dispatcher()

        fun create(): WeatherApiService {
            val okHttpClient = OkHttpClient.Builder()
            val interceptor = Interceptor {
                var request = it.request()
                val url = request.url().newBuilder().addQueryParameter("appid", App.applicationContext().getString(R.string.krungsri_api)).build()
                request = request.newBuilder().url(url).build()
                it.proceed(request)
            }
            okHttpClient.interceptors().add(interceptor)
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