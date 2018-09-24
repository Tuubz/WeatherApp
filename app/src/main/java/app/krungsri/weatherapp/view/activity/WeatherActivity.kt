package app.krungsri.weatherapp.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import app.krungsri.weatherapp.view.fragmentadapter.WeatherPagerAdapter
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherCurrentFragment
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherForeCastFragment
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather.*


class WeatherActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: WeatherPagerAdapter
    private var temperatureType = "metric"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.toolbar_right_icon) {
            if(temperatureType == "metric") {
                temperatureType = "imperial"
                item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_fahrenheit)
            } else {
                temperatureType = "metric"
                item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_celcius)
            }
            getData(temperatureType)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun init() {
        initViewPager()
        initToolbar()
        setListeners()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
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
                getData(temperatureType)
                return@OnKeyListener true
            }
            false
        })
    }

    private fun getData(units: String) {
        (supportFragmentManager.fragments[0] as WeatherCurrentFragment).getData(units)
        (supportFragmentManager.fragments[1] as WeatherForeCastFragment).getData(units)
    }
}
