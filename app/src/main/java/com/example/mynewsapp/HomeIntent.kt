package com.example.mynewsapp

sealed class HomeIntent {
    object LoadArticles : HomeIntent()
}