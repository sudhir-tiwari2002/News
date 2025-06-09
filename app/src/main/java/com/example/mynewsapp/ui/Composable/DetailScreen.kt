package com.example.mynewsapp.ui.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(articleUrl: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Article URL:",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = articleUrl,
            maxLines = 5,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )
    }

}
