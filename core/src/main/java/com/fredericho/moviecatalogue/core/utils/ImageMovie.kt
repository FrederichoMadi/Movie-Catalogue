package com.fredericho.moviecatalogue.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

object ImageMovie {

    const val API_IMAGE = "https://image.tmdb.org/t/p/"
    const val IMAGE_SIZE = "w500"

    fun setImage(context: Context, imagePath : String, imageView: ImageView){
        Glide.with(context).clear(imageView)
        Glide.with(context)
            .load(imagePath)
            .transform(RoundedCornersTransformation(25, 25))
            .apply(RequestOptions().override(550, 550))
            .into(imageView)
    }

    fun setImageDetail(context: Context, imagePath: String, imageView: ImageView){
        Glide.with(context).clear(imageView)
        Glide.with(context)
            .load(imagePath)
            .apply(RequestOptions.overrideOf(550,550))
            .into(imageView)
    }
}