package com.francisco.meliclone.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.francisco.imagemanager.loadUrlImage

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadUrlImage(view.context, url)
}