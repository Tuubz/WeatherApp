package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.krungsri.weatherapp.viewmodel.WeatherViewModel
import app.krungsri.weatherapp.widgets.GlideApp
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.fragment_weather_current.*


class WeatherCurrentFragment : Fragment() {

    private lateinit var weatherViewModel : WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_weather_current, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData("metric")
    }

    private fun init() {
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    private fun initObserver() {
        weatherViewModel.weather.observe(this, Observer {
            if(it != null) {
                temperatureText.text = "${it.metrics.temperature}"
                humidityText.text = "${it.metrics.humidity}"

                val imageName = "weather_${it.type.first().weather.toLowerCase()}"
                GlideApp.with(this)
                        .load(resources.getIdentifier(imageName, "drawable", context!!.packageName))
                        .into(background)
            }
        })
    }



    fun getData(units: String){
        weatherViewModel.getCurrentWeather(activity!!.cityInput.text.toString(), units)
    }


    companion object {
        fun newInstance(): WeatherCurrentFragment {
            return WeatherCurrentFragment()
        }
    }



}