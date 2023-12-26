package com.example.newsapp.room

import android.app.Application
import androidx.room.Room

class App : Application() {
/*
    companion object{
        lateinit var database: NewsDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }*/
}