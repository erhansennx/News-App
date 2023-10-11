package com.app.newsapp.model


import com.google.gson.annotations.SerializedName

data class LatestNews(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)