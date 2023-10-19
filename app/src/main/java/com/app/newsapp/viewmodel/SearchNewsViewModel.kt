package com.app.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.newsapp.model.NewsModel
import com.app.newsapp.service.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchNewsViewModel : ViewModel() {

    private val newsApiService = NewsService()
    private val disposable = CompositeDisposable()
    val newsModel = MutableLiveData<NewsModel?>()

    fun getAllNews() {
        disposable.add(
            newsApiService.getAllNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsModel>() {
                    override fun onSuccess(t: NewsModel) {
                        newsModel.value = t
                    }

                    override fun onError(e: Throwable) {
                        newsModel.value = null
                    }
                })
        )
    }


}