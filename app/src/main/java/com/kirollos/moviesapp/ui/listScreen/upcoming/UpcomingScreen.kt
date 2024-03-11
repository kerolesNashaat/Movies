package com.kirollos.moviesapp.ui.listScreen.upcoming

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirollos.common.components.ErrorContent
import com.kirollos.moviesapp.R
import com.kirollos.moviesapp.ui.listScreen.ShowMoviesList

@Composable
fun UpcomingScreen(
    viewModel: UpcomingViewModel = hiltViewModel(),
    onCardClick: (movieId: Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val config = state.config

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.movie != null -> ShowMoviesList(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                movie = state.movie!!,
                loadingItem = state.loadingItem,
                configurations = config,
                onCardClick = onCardClick,
                onReachedListEnd = {
                    viewModel.processIntent(UpcomingIntent.LoadMoreMovies(it))
                }
            )

            state.error != null -> ErrorContent(
                errorMessage = state.error ?: stringResource(id = R.string.failedToGetData),
                refreshIcon = R.drawable.icon_refresh,
                onRefreshIconClicked = {
                    viewModel.processIntent(UpcomingIntent.GetUpcomingMovies(1))
                }
            )

            state.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}