package com.app.newsapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.newsapp.model.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun initDao() : NewsDao

    companion object {

        @Volatile
        private var newsDatabase : NewsDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = newsDatabase ?: synchronized(lock) { // More than one thread cannot access it at the same time
            newsDatabase ?: createDatabase(context).also {
                newsDatabase = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "newsdatabase"
        ).build()

    }

}