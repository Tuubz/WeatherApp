package app.krungsri.weatherapp.view.fragmentadapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherForeCastFragment
import app.krungsri.weatherapp.view.fragmentadapter.fragments.WeatherFragment

class WeatherPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> WeatherFragment.newInstance()
            else -> WeatherForeCastFragment.newInstance()
        }
    }

    override fun getItemPosition(item: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return 2
    }


}