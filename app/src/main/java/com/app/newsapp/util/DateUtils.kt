package com.app.newsapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun convertPublishedDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val convertDate = inputFormat.parse(date)
        return outputFormat.format(convertDate!!)
    }

}