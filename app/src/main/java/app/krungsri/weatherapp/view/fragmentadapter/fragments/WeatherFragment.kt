package app.krungsri.weatherapp.view.fragmentadapter.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import app.krungsri.weatherapp.viewmodel.WeatherViewModel
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : Fragment() {

    private lateinit var weatherViewModel : WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListeners()
        getData()
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
            }
        })
    }

    private fun getData(){
        weatherViewModel.getCurrentWeather(activity!!.cityInput.text.toString(), "metric")
    }

    private fun setListeners() {
        activity!!.cityInput.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getData()
                return@OnKeyListener true
            }
            false
        })
    }

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }



}