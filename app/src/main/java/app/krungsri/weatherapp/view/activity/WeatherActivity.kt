package app.krungsri.weatherapp.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import app.krungsri.weatherapp.view.fragmentadapter.WeatherPagerAdapter
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherCurrentFragment
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherForeCastFragment
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*


class WeatherActivity : AppCompatActivity() {


    private lateinit var pagerAdapter: WeatherPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        init()
    }

    private fun init() {
        initViewPager()
        initToolbar()

        setListeners()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        toolbar.apply {
//            setNavigationIcon(R.drawable.ic_refresh)
        }
        supportActionBar?.title = ""
    }


    private fun initViewPager() {
        pagerAdapter = WeatherPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setListeners() {
        cityInput.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                (supportFragmentManager.fragments[0] as WeatherCurrentFragment).getData()
                (supportFragmentManager.fragments[1] as WeatherForeCastFragment).getData()
                return@OnKeyListener true
            }
            false
        })
    }



}
