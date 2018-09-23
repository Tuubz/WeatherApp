package app.krungsri.weatherapp.widgets

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        val requestOptions = RequestOptions().centerCrop()
        builder?.setDefaultRequestOptions(requestOptions)
    }
}
