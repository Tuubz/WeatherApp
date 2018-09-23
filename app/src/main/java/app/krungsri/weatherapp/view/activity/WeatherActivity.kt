package app.krungsri.weatherapp.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.krungsri.weatherapp.view.fragmentadapter.WeatherPagerAdapter
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
    }

}
