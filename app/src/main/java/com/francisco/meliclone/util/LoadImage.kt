package com.francisco.meliclone.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.francisco.imagemanager.loadUrlImage
import com.synnapps.carouselview.CarouselView

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadUrlImage(view.context, url)
}

@BindingAdapter("android:imageListUrl")
fun loadCarouselView(view: CarouselView, list: List<String>?) {
    list?.let {
        view.setImageListener { position, imageView ->
            imageView.loadUrlImage(view.context, it[position])
        }
        view.pageCount = it.count()
    }
}