package com.app.newsapp.service

import com.app.newsapp.model.NewsModel
import io.reactivex.Single
import retrofit2.http.GET

interface NewsAPI {

    // https://newsapi.org/v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5
    // Base URL: https://newsapi.org/
    // Extension URL: v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5
    @GET("v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5")
    fun getTopHeadlinesNews(): Single<NewsModel> //Single is a class of rxjava.

    @GET("v2/everything?q=all&apiKey=95ec4425610e42f09f423c65d29cd3a5")
    fun getAllNews(): Single<NewsModel>

    // Observable, Flowable, Single, Maybe, Completable => 5 type.
    // Single: Retrieves data once

}