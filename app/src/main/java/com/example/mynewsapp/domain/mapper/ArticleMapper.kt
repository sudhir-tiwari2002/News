package com.example.mynewsapp.domain.mapper

import com.example.mynewsapp.data.model.ArticleDto

fun ArticleDto.toDomain(): Article = Article(
    title = title,
    imageUrl = urlToImage,
    author = author,
    source = source.name,
    publishedAt = publishedAt,
    description = description,
    url = url
)
