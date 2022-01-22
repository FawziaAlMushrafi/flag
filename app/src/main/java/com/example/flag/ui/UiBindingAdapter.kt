package com.example.flag.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.flag.R
import com.example.flag.internet.DataItem
import com.example.flag.overview.CountryGridAdapter

@BindingAdapter("imageUrl")
fun ImageView.findUrl(imgUrl: String?) {
    imgUrl?.let {
        var imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        this.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }
}

@BindingAdapter("listData")
fun RecyclerView.bindRecycleView(data: List<DataItem>?) {
    if (this.adapter == null) {
        this.adapter = CountryGridAdapter()
    }
    val adapter = this.adapter as CountryGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUri")
fun ImageView.findUrl2(imgUrl: String?) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .data(imgUrl)
        .target(this)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_baseline_broken_image_24)
        .build()

    imageLoader.enqueue(request)
}