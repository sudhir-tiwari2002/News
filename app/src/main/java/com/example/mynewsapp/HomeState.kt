package com.example.mynewsapp

import com.example.mynewsapp.domain.mapper.Article

data class HomeState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)