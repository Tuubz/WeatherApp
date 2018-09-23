package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.krungsri.weatherapp.view.recyclerview.WeatherAdapter
import app.krungsri.weatherapp.viewmodel.WeatherForecastViewModel
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.fragment_weather_forecast.*


class WeatherForeCastFragment : Fragment() {

    private lateinit var weatherForecastViewModel : WeatherForecastViewModel
    private lateinit var recyclerAdapter: WeatherAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
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
                recyclerAdapter.load(it.weathers)
            }
        })
    }

    private fun initRecyclerView() {
        recyclerAdapter = WeatherAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = recyclerAdapter
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