package com.app.newsapp.util

import androidx.room.TypeConverter
import com.app.newsapp.model.Source

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.id
    }
    @TypeConverter
    fun toSource(id: String?): Source? {
        return id?.let { Source(it, "") }
    }

}