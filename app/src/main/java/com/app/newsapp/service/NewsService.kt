package com.app.newsapp.service

import com.app.newsapp.model.NewsModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsService {

    private val baseURL = "https://newsapi.org/"

    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getLatestNews(): Single<NewsModel> {
        return api.getTopHeadlinesNews()
    }

    fun getAllNews(): Single<NewsModel> {
        return api.getAllNews()
    }

}