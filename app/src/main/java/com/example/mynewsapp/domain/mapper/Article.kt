package com.example.mynewsapp.domain.mapper

data class Article(
    val title: String,
    val imageUrl: String?,
    val author: String?,
    val source: String,
    val publishedAt: String,
    val description: String?,
    val url: String
)
