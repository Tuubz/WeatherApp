package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.krungsri.weatherapp.viewmodel.WeatherForecastViewModel
import app.krungsri.weatherapp.viewmodel.WeatherViewModel
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherForeCastFragment : Fragment() {

    private lateinit var weatherForecastViewModel : WeatherForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
    }


    private fun init() {
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        weatherForecastViewModel = ViewModelProviders.of(this).get(WeatherForecastViewModel::class.java)
    }

    private fun initObserver() {
        weatherForecastViewModel.weathers.observe(this, Observer {
            if(it != null) {
//                temperatureText.text = "${it.metrics.temperature}"
//                humidityText.text = "${it.metrics.humidity}"
            }
        })
    }

    private fun getData(){
        weatherForecastViewModel.getForecastWeather(activity!!.cityInput.text.toString(), "metric", 7)
    }



    companion object {
        fun newInstance(): WeatherForeCastFragment {
            return WeatherForeCastFragment()
        }
    }


}