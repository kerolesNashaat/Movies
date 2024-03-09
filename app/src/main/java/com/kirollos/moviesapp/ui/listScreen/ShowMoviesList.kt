package com.kirollos.moviesapp.ui.listScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.kirollos.common.components.GetText
import com.kirollos.common.size_8dp
import com.kirollos.moviesapp.R
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie

@Composable
fun ShowMoviesList(
    modifier: Modifier,
    lazyMovies: LazyPagingItems<Movie>,
    configurations: Configurations?,
    onCardClick: (movieId: Int) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            count = lazyMovies.itemCount,
            key = lazyMovies.itemKey { it.id },
            contentType = lazyMovies.itemContentType()
        ) { index ->
            val movie = remember { lazyMovies[index] }
            movie?.resultList?.forEach { result ->
                configurations?.let { config ->
                    val imageUrl = remember {
                        getImageUrl(
                            config, posterPath = result!!.posterPath ?: "",
                            imageResolution = "780"
                        )
                    }

                    MovieItemCard(
                        modifier = Modifier.padding(size_8dp),
                        title = result!!.title,
                        releaseDate = result.releaseDate,
                        imageUrl = imageUrl
                    ) { onCardClick(result.id!!) }
                }
            }
        }

        item {
            if (lazyMovies.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

fun getImageUrl(
    config: Configurations, posterPath: String,
    imageResolution: String
): String {
    val imageUrl = StringBuilder("")
    val imageBaseUrl = config.images?.secureBaseUrl
    val size = config.images?.posterSizes
        ?.find { it?.contains(imageResolution)!! }.toString()

    return imageUrl
        .append(imageBaseUrl)
        .append(size)
        .append("/")
        .append(posterPath)
        .toString()
}

@Composable
fun MovieItemCard(
    modifier: Modifier,
    title: String?,
    releaseDate: String?,
    imageUrl: String?,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.clickable { onCardClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GetImageFromUrl(imageUrl)
            Spacer(modifier = Modifier.height(size_8dp))
            GetText(text = title ?: "", style = MaterialTheme.typography.bodyLarge)
            GetText(text = releaseDate ?: "", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun GetImageFromUrl(imageUrl: String?) {
    AsyncImage(
        model = imageUrl, contentDescription = null,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        error = painterResource(id = R.drawable.baseline_report_gmailerrorred_24)
    )
//    SubcomposeAsyncImage(
//        model = imageUrl,
//        loading = {
//            Box(modifier = Modifier.size(size_300dp, size_400dp)) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .size(size_32dp),
//                    color = MaterialTheme.colorScheme.secondary
//                )
//            }
//        },
//        error = {
//            Image(
//                imageVector = ImageVector.vectorResource(
//                    id = R.drawable.baseline_report_gmailerrorred_24
//                ),
//                modifier = Modifier.size(size_300dp, size_400dp),
//                contentDescription = null
//            )
//        },
//        contentDescription = null
//    )
}
