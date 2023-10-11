package com.app.newsapp.service

import com.app.newsapp.model.LatestNews
import io.reactivex.Single
import retrofit2.http.GET

interface NewsAPI {

    // https://newsapi.org/v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5
    // Base URL: https://newsapi.org/
    // Extension URL: v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5
    @GET("v2/top-headlines?country=us&apiKey=95ec4425610e42f09f423c65d29cd3a5")
    fun getTopHeadlinesNews(): Single<LatestNews> //Single is a class of rxjava.

    // Observable, Flowable, Single, Maybe, Completable => 5 type.
    // Single: Retrieves data once

}