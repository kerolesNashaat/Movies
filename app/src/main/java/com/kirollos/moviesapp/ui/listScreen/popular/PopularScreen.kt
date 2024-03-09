package com.kirollos.moviesapp.ui.listScreen.popular

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kirollos.moviesapp.ui.listScreen.ShowMoviesList
import kotlinx.coroutines.delay

@Composable
fun PopularScreen(
    viewModel: PopularViewModel = hiltViewModel(),
    onCardClick: (movieId: Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyMovies = state.moviesFlow!!.collectAsLazyPagingItems()
    val config = state.config
    val context = LocalContext.current

    LaunchedEffect(key1 = lazyMovies.loadState) {
        when (lazyMovies.loadState.refresh) {
            is LoadState.Loading -> {}

            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    (lazyMovies.loadState.refresh as LoadState.Error).error.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is LoadState.NotLoading -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (lazyMovies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else ShowMoviesList(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            lazyMovies = lazyMovies, configurations = config,
            onCardClick = onCardClick
        )
    }
}
