package io.issc.mod_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.issc.mod_compose.ui.theme.Android_dev_tutorial_ktTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

data class GalleryImage(
    val id: Int,
    val url: String,
    val heightDp: Int,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WaterfallImageGallery(
    images: List<GalleryImage>,
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    isLoading: Boolean = false,
    onLoadMore: () -> Unit = {},
) {
    LaunchedEffect(state, images.size, isLoading) {
        snapshotFlow {
            val layoutInfo = state.layoutInfo
            val totalCount = layoutInfo.totalItemsCount
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            totalCount > 0 && lastVisible >= totalCount - 6
        }
            .distinctUntilChanged()
            .filter { it && !isLoading }
            .collect { onLoadMore() }
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = state,
        modifier = modifier.fillMaxSize(),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp),
    ) {
        items(images, key = { it.id }) { image ->
            AsyncImage(
                model = image.url,
                contentDescription = "Gallery image ${image.id}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(image.heightDp.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            )
        }

        if (isLoading) {
            items(listOf("loading"), key = { it }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun WaterfallGalleryDemoScreen(modifier: Modifier = Modifier) {
    val images = remember { mutableStateListOf<GalleryImage>() }
    var page by remember { mutableIntStateOf(0) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    suspend fun loadNextPage() {
        if (isLoading) return
        isLoading = true
        delay(500)
        val start = page * 20
        images.addAll(
            List(20) { index ->
                val id = start + index + 1
                GalleryImage(
                    id = id,
                    // Picsum gives deterministic pseudo-random sample images.
                    url = "https://picsum.photos/seed/waterfall_$id/600/900",
                    heightDp = 140 + (id * 37 % 170),
                )
            },
        )
        page++
        isLoading = false
    }

    LaunchedEffect(Unit) {
        loadNextPage()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Waterfall Gallery",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
        )

        WaterfallImageGallery(
            images = images,
            isLoading = isLoading,
            onLoadMore = {
                if (!isLoading) {
                    scope.launch { loadNextPage() }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WaterfallGalleryPreview() {
    Android_dev_tutorial_ktTheme {
        WaterfallGalleryDemoScreen()
    }
}
