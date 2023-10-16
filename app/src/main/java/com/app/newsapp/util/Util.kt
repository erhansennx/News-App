package com.app.newsapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.newsapp.R
import com.bumptech.glide.Glide

fun ImageView.downloadImage(url: String?) {

    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.progress_animation)
        .error(R.drawable.ic_launcher_background)
        .into(this)

}

@BindingAdapter("android:downloadImage")
fun getImage(view: ImageView, url: String?) {
    view.downloadImage(url)
}

@BindingAdapter("android:formatDate")
fun convertPublishedDate(view: TextView, publishedDate: String) {
    val date = DateUtils.convertPublishedDate(publishedDate)
    view.text = date
}