package app.krungsri.weatherapp.view.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.krungsri.weatherapp.model.Weather
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.list_item_weather.view.*


class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>()  {

    private var weathers: ArrayList<Weather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WeatherAdapter.WeatherHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather, parent, false)
        return WeatherHolder(v)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherHolder, position: Int) {
        holder.temperature.text = "${weathers[position].metrics.temperature}"
    }

    fun load(newWeathers : ArrayList<Weather>) {
        weathers.addAll(newWeathers)
        notifyDataSetChanged()
    }

    class WeatherHolder(v: View) : RecyclerView.ViewHolder(v) {
        val temperature : TextView = v.temperatureText
    }

}



