package com.app.newsapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.app.newsapp.model.Article
import com.app.newsapp.service.NewsDatabase
import kotlinx.coroutines.launch

class NewsDetailViewModel(application: Application) : BaseViewModel(application) {

    val articleLiveData = MutableLiveData<List<Article>>()
    private val newsDao = NewsDatabase(getApplication()).initDao()

    fun shareTheNewsUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, url)
        context.startActivity(Intent.createChooser(intent, "Share"))
    }

    fun saveInDatabase(article: Article) {
        launch {
            newsDao.insertArticle(article)
        }
    }

    fun getSavedNews() {
        launch {
            articleLiveData.value = newsDao.getAllArticles()
        }
    }

}