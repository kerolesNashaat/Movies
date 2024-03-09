package com.kirollos.moviesapp.ui.listScreen.nowPlaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.moviesapp.domain.GetNowPlayingMoviesUseCase
import com.kirollos.moviesapp.ui.utils.ListUiState
import com.kirollos.network.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(ListUiState())
    var uiState = _uiState.asStateFlow()

    init {
        processIntent(NowPlayingIntent.GetConfigurations)
        processIntent(NowPlayingIntent.GetNowPlayingMovies)
    }

    fun processIntent(intent: NowPlayingIntent) {
        when (intent) {
            is NowPlayingIntent.GetNowPlayingMovies -> getNowPlayingMovies()
            NowPlayingIntent.GetConfigurations -> getConfig()
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            val moviesFlow = getNowPlayingMoviesUseCase.invoke()
            _uiState.update { it.copy(moviesFlow = moviesFlow) }
        }
    }

    private fun getConfig() {
        viewModelScope.launch {
            getConfigurationUseCase.invoke().collectLatest { res ->
                when (res) {
                    is Resource.Failure -> {}
                    is Resource.Success -> _uiState.update { it.copy(config = res.data) }
                }
            }
        }
    }
}