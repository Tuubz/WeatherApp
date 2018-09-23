package nimbl3.surveyapp.widgets

import android.content.Context
import app.weather.krungsi.weatherapp.R
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.request.RequestOptions


@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
//        val requestOptions = RequestOptions().centerCrop().error(R.drawable.ic_close)
        val requestOptions = RequestOptions().centerCrop()
        builder?.setDefaultRequestOptions(requestOptions)
    }
}
