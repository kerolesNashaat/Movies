package com.kirollos.moviesapp.ui.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirollos.common.components.ErrorContent
import com.kirollos.common.size_45dp
import com.kirollos.moviesapp.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel<SplashViewModel>(),
    onSplashTaskCompleted: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffect = viewModel.sideEffect

    LaunchedEffect(key1 = sideEffect) {
        sideEffect.collectLatest {
            when (it) {
                SplashSideEffect.Navigate -> onSplashTaskCompleted()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        when {
            state.error != null -> {
                ErrorContent(
                    errorMessage = state.error?.toString()
                        ?: stringResource(id = R.string.failedToGetData),
                    refreshIcon = R.drawable.icon_refresh,
                    onRefreshIconClicked = {
                        viewModel.processIntent(SplashIntent.DoSplashTask)
                    }
                )
            }

            state.loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(size_45dp)
                )
            }
        }

    }

}