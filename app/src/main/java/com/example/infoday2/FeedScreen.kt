package com.example.infoday2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.infoday2.ui.theme.Infoday2Theme
import kotlinx.serialization.Serializable

@Serializable
data class Feed(val id: Int, val image: String, val title: String, val detail: String) {
    companion object {
        val data = listOf(
            Feed(
                123, "https://cdn.stocksnap.io/img-thumbs/960w/philadelphia-travel_LPDQBLM2A0.jpg", " COMP ", " discount "
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(feeds: List<Feed>) {
    LazyColumn {
        items(feeds) { feed ->
            Card(
                onClick = { /* Do something */ },
                modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                Column {
                    AsyncImage(
                        model = feed.image,
                        contentDescription = null
                    )
                    Box(Modifier.fillMaxSize()) {
                        Text(feed.title, Modifier.align(Alignment.Center))
                    }
                }
            }
            Text(feed.title)
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedPreview() {
    Infoday2Theme {
        FeedScreen(Feed.data)
    }
}
