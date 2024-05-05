package com.devpro.android54_day11.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapter {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindUrl2Image(imageView: ImageView, url: String) {
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}