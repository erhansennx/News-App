package com.app.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Article(
    @ColumnInfo(name = "author")
    @SerializedName("author")
    var author: String?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @ColumnInfo(name = "source")
    @SerializedName("source")
    val source: Source?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,
    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String?
):Serializable {
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0
}