package com.kirollos.moviesapp.ui.listScreen.upcoming

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirollos.common.components.ErrorContent
import com.kirollos.common.components.LoadingContent
import com.kirollos.moviesapp.R
import com.kirollos.moviesapp.ui.utils.ListUiState
import com.kirollos.moviesapp.ui.listScreen.GetMoviesRow
import com.kirollos.network.domain.model.Movie

@Composable
fun UpcomingScreen(
    viewModel: UpcomingViewModel = hiltViewModel(),
    onCardClick: (movieId: Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        state.movies != null -> SuccessContent(viewModel, state, onCardClick)
        state.error != null -> {
            ErrorContent(errorMessage = if (state.error?.isNotBlank() == true) state.error!!
            else stringResource(id = R.string.failedToGetData),
                refreshIcon = R.drawable.icon_refresh,
                onRefreshIconClicked = {
                    viewModel.processIntent(
                        UpcomingIntent.GetUpcomingMovies(
                            language = Locale.current.language, page = 1
                        )
                    )
                })
        }

        state.loading -> LoadingContent()
    }
}

@Composable
fun SuccessContent(viewModel: UpcomingViewModel, state: ListUiState,
    onCardClick: (movieId: Int) -> Unit) {
    val movie = state.movies as Movie
    if (movie.resultList?.isNotEmpty() == true) {
        GetMoviesRow(
            modifier = Modifier.fillMaxSize(),
            resultList = movie.resultList!!,
            config = state.config,
            onCardClick = onCardClick
        )
    } else ErrorContent(
        errorMessage = if (state.error?.isNotBlank() == true) state.error
        else stringResource(id = R.string.failedToGetData),
        refreshIcon = R.drawable.icon_refresh,
        onRefreshIconClicked = {
            viewModel.processIntent(
                UpcomingIntent.GetUpcomingMovies(
                    language = Locale.current.language, page = 1
                )
            )
        })
}
