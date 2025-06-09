package com.example.mynewsapp.data.api

import com.example.mynewsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("language") language: String ,
//        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}