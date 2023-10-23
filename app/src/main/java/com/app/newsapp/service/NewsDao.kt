package com.app.newsapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.newsapp.model.Article

@Dao
interface NewsDao {
    @Insert
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM article")
    suspend fun getAllArticles() : List<Article>

}