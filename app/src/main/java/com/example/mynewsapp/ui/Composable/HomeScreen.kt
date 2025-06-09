package com.example.mynewsapp.ui.Composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynewsapp.HomeIntent
import com.example.mynewsapp.HomeViewModel
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import com.example.mynewsapp.isInternetAvailable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleClick: (String) -> Unit,
    onBookmarkClick: () -> Unit
) {

    val context = LocalContext.current
    val isOnline = isInternetAvailable(context)

    val state by viewModel.state.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.onIntent(HomeIntent.LoadArticles)
    }


//    Log.i("State" , state.articles[0].title)
    val articles = state.articles

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "News Article",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Bookmarks"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if(!isOnline){
            Text(
                text = "No Internet Available",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        }
        else if (articles.isEmpty()) {
            Text(
                text = "No articles available",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(articles) { article ->
                    ArticleItem(
                        article = article,
                        onClick = { article.url?.let(onArticleClick) },
                        onBookmark = {
                            article.url?.let {
                                Log.i("Bookmark", "Toggled bookmark for ${article.title}")
                            }
                        }
                            )
                }
            }
        }
    }
    }





