package com.example.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsConverter::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "notes"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}