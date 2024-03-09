package com.kirollos.moviesapp.ui.movieDetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kirollos.common.components.ErrorContent
import com.kirollos.common.components.GetText
import com.kirollos.common.components.LoadingContent
import com.kirollos.common.size_16dp
import com.kirollos.common.size_8dp
import com.kirollos.moviesapp.R
import com.kirollos.moviesapp.ui.listScreen.GetImageFromUrl
import com.kirollos.moviesapp.ui.listScreen.getImageUrl
import com.kirollos.moviesapp.ui.utils.ListUiState

@Composable
fun MovieDetailScreen(viewModel: MovieDetailsViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        state.error != null -> ErrorContent(
            errorMessage = state.error ?: stringResource(id = R.string.failedToGetData),
            refreshIcon = R.drawable.icon_refresh,
            onRefreshIconClicked = {
                viewModel.processIntent(
                    MovieDetailsIntent.GetMovieDetails(
                        language = Locale.current.language,
                        movieId = state.movieId?.toInt() ?: 0
                    )
                )
            }
        )

        state.movieDetail != null -> MovieDetailContent(state, viewModel)

        state.loading -> LoadingContent()
    }
}

@Composable
fun MovieDetailContent(state: ListUiState, viewModel: MovieDetailsViewModel) {
    val config = state.config
    val movieDetails = state.movieDetail
    if (config != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = remember {
                getImageUrl(config, posterPath = movieDetails!!.posterPath ?: "",
                    imageResolution = "original")
            }

            GetImageFromUrl(imageUrl = imageUrl)

            Column(
                modifier = Modifier.padding(size_16dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GetText(text = movieDetails!!.overview ?: "")
                Spacer(modifier = Modifier.height(size_16dp))
                val genres = remember { StringBuilder("") }
                movieDetails.genres?.map { it?.name }?.forEachIndexed { index, name ->
                    genres.append(name)
                    if (index != movieDetails.genres!!.size - 1) genres.append(", ")
                }
                GetText(text = genres.toString(), color = MaterialTheme.colorScheme.tertiary)
            }
        }
        Spacer(modifier = Modifier.height(size_8dp))
    } else ErrorContent(
        errorMessage = state.error ?: stringResource(id = R.string.failedToGetData),
        refreshIcon = R.drawable.icon_refresh,
        onRefreshIconClicked = {
            viewModel.processIntent(
                MovieDetailsIntent.GetMovieDetails(
                    language = Locale.current.language,
                    movieId = state.movieId?.toInt() ?: 0
                )
            )
        }
    )
}
