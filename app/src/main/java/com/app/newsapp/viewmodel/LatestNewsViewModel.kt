package com.app.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.newsapp.model.LatestNews
import com.app.newsapp.service.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LatestNewsViewModel : ViewModel() {

    private val newsApiService = NewsService()
    private val disposable = CompositeDisposable()
    val latestNews = MutableLiveData<LatestNews?>()

    fun getData() {
        disposable.add(
            newsApiService.getLatestNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LatestNews>(){
                    override fun onSuccess(t: LatestNews) {
                        latestNews.value = t
                    }

                    override fun onError(e: Throwable) {
                        latestNews.value = null
                    }

                })
        )
    }

}