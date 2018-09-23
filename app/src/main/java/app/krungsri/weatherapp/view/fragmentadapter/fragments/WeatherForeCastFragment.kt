package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.weather.krungsi.weatherapp.R


class WeatherForeCastFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    companion object {
        fun newInstance(): WeatherForeCastFragment {
            return WeatherForeCastFragment()
        }
    }


}