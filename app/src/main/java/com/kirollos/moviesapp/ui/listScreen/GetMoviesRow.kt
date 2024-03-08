package com.kirollos.moviesapp.ui.listScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import coil.compose.SubcomposeAsyncImage
import com.kirollos.common.components.GetText
import com.kirollos.common.size_300dp
import com.kirollos.common.size_32dp
import com.kirollos.common.size_400dp
import com.kirollos.common.size_8dp
import com.kirollos.moviesapp.R
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Result

@Composable
fun GetMoviesRow(
    modifier: Modifier, resultList: List<Result?>, config: Configurations?,
    onCardClick: (movieId: Int) -> Unit
) {
    Box(modifier = modifier) {
        LazyRow(modifier = Modifier.align(Alignment.Center)) {
            itemsIndexed(items = resultList) { index, result ->
                if (config != null) {
                    val imageUrl = remember { StringBuilder("") }
                    val imageBaseUrl = config.images?.secureBaseUrl
                    val size =
                        config.images?.posterSizes?.find { it?.contains("780")!! }.toString()
                    imageUrl.append(imageBaseUrl).append(size).append("/")
                        .append(result!!.posterPath)
                    if (index == 0) Spacer(modifier = Modifier.padding(start = size_8dp))

                    MovieItemCard(
                        modifier = Modifier,
                        title = result.title,
                        releaseDate = result.releaseDate,
                        imageUrl = imageUrl.toString(),
                        onCardClick = { onCardClick(result.id!!) }
                    )

                    Spacer(modifier = Modifier.padding(end = size_8dp))
                }
            }
        }
    }
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
            SubcomposeAsyncImage(
                model = imageUrl,
                loading = {
                    Box(modifier = Modifier.size(size_300dp, size_400dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(size_32dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                error = {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_report_gmailerrorred_24
                        ),
                        modifier = Modifier.size(size_300dp, size_400dp),
                        contentDescription = null
                    )
                },
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(size_8dp))
            GetText(text = title ?: "", style = MaterialTheme.typography.bodyLarge)
            GetText(text = releaseDate ?: "", style = MaterialTheme.typography.bodyLarge)
        }
    }
}