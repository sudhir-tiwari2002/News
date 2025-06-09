package com.example.mynewsapp.data.repository

import android.util.Log
import com.example.mynewsapp.data.api.NewsApiService
import com.example.mynewsapp.domain.mapper.Article
import com.example.mynewsapp.domain.mapper.toDomain
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    @Named("NEWS_API_KEY") private val apiKey: String
) {
    suspend fun getArticles(): List<Article> {
        val date = LocalDate.now().toString() // "2025-06-07"
        val response = api.getArticles(
            query = "tesla",
            from = "2025-05-08",
            sortBy = "publishedAt",
            language = "en",
            apiKey = apiKey
        )

        return response.articles.map { it.toDomain() }
    }
}
