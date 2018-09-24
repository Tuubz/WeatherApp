package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.krungsri.weatherapp.viewmodel.WeatherCurrentViewModel
import app.krungsri.weatherapp.widgets.GlideApp
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.fragment_weather_current.*
import java.text.SimpleDateFormat
import java.util.*



class WeatherCurrentFragment : Fragment() {

    private lateinit var weatherViewModel : WeatherCurrentViewModel
    private var unitsTemp = "metric"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_weather_current, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData(unitsTemp)
    }

    private fun init() {
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherCurrentViewModel::class.java)
    }

    private fun initObserver() {
        weatherViewModel.weather.observe(this, Observer {
            if(it != null) {

                //Date
                val date = Date(it.date * 1000)
                val fmt = SimpleDateFormat("dd/MM/yyyy", Locale.TAIWAN)
                fmt.timeZone = TimeZone.getTimeZone("Asia/Thailand")
                dateText.text = fmt.format(date)

                //Details
                temperatureText.text = "${Math.round(it.metrics.temperature)}"
                humidityText.text = "Humidity: ${it.metrics.humidity}%"

                //WeatherType
                val weather = it.type.first().weather
                weatherType.text = weather

                //Background
                val id = resources.getIdentifier(weather.toLowerCase(), "color", context!!.packageName)
                background.setBackgroundColor(ResourcesCompat.getColor(resources, id, null))

                //Icon
                GlideApp.with(this)
                        .load(resources.getIdentifier("ic_${weather.toLowerCase()}", "drawable", context!!.packageName))
                        .into(bigWeatherIcon)
            }
        })
    }

    fun getData(units: String){
        unitsTemp = units
        switchDegrees(unitsTemp)
        weatherViewModel.getCurrentWeather(activity!!.cityInput.text.toString(), units)
    }

    private fun switchDegrees(units: String) {
        if(units == "metric") {
            degreesType.text = resources.getString(R.string.celsius)
        } else {
            degreesType.text = resources.getString(R.string.fahrenheit)
        }
    }

    companion object {
        fun newInstance(): WeatherCurrentFragment {
            return WeatherCurrentFragment()
        }
    }



}