package app.krungsri.weatherapp.view.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.krungsri.weatherapp.App
import app.krungsri.weatherapp.model.Weather
import app.krungsri.weatherapp.widgets.GlideApp
import app.weather.krungsi.weatherapp.R
import kotlinx.android.synthetic.main.fragment_weather_current.*
import kotlinx.android.synthetic.main.list_item_weather.view.*
import java.text.SimpleDateFormat
import java.util.*


class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>()  {

    private var weathers: ArrayList<Weather> = ArrayList()
    private lateinit var unitsTemp: String

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WeatherAdapter.WeatherHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather, parent, false)
        return WeatherHolder(v)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherHolder, position: Int) {
        val date = Date(weathers[position].date * 1000)
        val fmt = SimpleDateFormat("HH:mm:ss", Locale.TAIWAN)
        fmt.timeZone = TimeZone.getTimeZone("Asia/Thailand")

        holder.dateText.text = fmt.format(date)
        holder.weatherText.text = weathers[position].type.first().weather
        holder.temperatureText.text = "${Math.round(weathers[position].metrics.temperature)}${switchDegrees(unitsTemp)}"
        holder.humidityText.text = "${weathers[position].metrics.humidity}%"

        val imageName = "ic_${weathers[position].type.first().weather.toLowerCase()}"
        val context = holder.itemView.context
        GlideApp.with(context)
                .load(context.resources.getIdentifier(imageName, "drawable", context.packageName))
                .into(holder.icon)

    }

    fun load(newWeathers : ArrayList<Weather>, units: String) {
        unitsTemp = units
        clear()
        weathers.addAll(newWeathers)
        notifyDataSetChanged()
    }

    fun clear(){
        weathers.clear()
        notifyDataSetChanged()
    }

    private fun switchDegrees(units: String) : String {
        if(units == "metric") {
            return App.applicationContext().resources.getString(R.string.celsius)
        } else {
            return App.applicationContext().resources.getString(R.string.fahrenheit)
        }
    }

    class WeatherHolder(v: View) : RecyclerView.ViewHolder(v) {
        val dateText : TextView = v.dateSmallText
        val weatherText : TextView = v.weatherSmallText
        val temperatureText : TextView = v.temperatureSmallText
        val humidityText : TextView = v.humiditySmallText
        val icon : ImageView = v.weatherIcon


    }

}



