package com.example.mynewsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadArticles -> loadArticles()
        }
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _state.value = HomeState(isLoading = true)
            try {
                val articles = repository.getArticles()
                Log.d("HomeViewModel", "Fetched ${articles.size} articles")
                _state.value = HomeState(articles = articles)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching articles", e)
                _state.value = HomeState(error = e.localizedMessage ?: "Unknown error")
            }
        }
    }
}