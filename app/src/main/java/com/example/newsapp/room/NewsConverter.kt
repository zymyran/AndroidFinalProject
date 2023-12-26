package com.example.newsapp.room

import androidx.room.TypeConverter
import com.example.newsapp.model.Source

class NewsConverter {
    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }
    @TypeConverter
    fun fromString(name: String) : Source{
        return Source(name,name)
    }
}