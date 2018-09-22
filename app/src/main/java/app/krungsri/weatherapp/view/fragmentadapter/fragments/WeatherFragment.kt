package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.weather.krungsi.weatherapp.R


class WeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }



}