package com.example.newsapp.mvvm

import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.room.NewsDatabase
import retrofit2.Response
import retrofit2.http.Query

class NewsRepository(val db : NewsDatabase) {
    private val api : NewsApi = RetrofitInstance().api
    suspend fun getBreakingNews(countryCode : String,pageNumber : Int) : Response<NewsResponse>{
        return api.getBreakingNews(countryCode,pageNumber)
    }

    suspend fun searchNews(searchQuery: String,pageNumber: Int): Response<NewsResponse> {
        return api.searchForNews(searchQuery,pageNumber)
    }

    suspend fun upsert(article: Article) = db.newsDao().upsert(article)
    fun getAllArticles() = db.newsDao().getAllArticles()
    suspend fun delete(article: Article) = db.newsDao().delete(article)
}