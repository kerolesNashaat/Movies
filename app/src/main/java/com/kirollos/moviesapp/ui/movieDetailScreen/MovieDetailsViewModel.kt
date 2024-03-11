package com.kirollos.moviesapp.ui.movieDetailScreen

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.moviesapp.domain.GetMovieDetailsUseCase
import com.kirollos.moviesapp.ui.utils.ListUiState
import com.kirollos.moviesapp.ui.utils.PARAM_MOVIE_ID
import com.kirollos.dataSource.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(ListUiState())
    var uiState = _uiState.asStateFlow()

    init {
        processIntent(MovieDetailsIntent.GetConfigurations)

        val movieId = savedStateHandle.get<String>(PARAM_MOVIE_ID)
        if (movieId != null) {
            _uiState.update { it.copy(movieId = movieId) }
            processIntent(
                MovieDetailsIntent.GetMovieDetails(
                    language = Locale.current.language,
                    movieId = movieId.toInt()
                )
            )
        }
    }

    fun processIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.GetMovieDetails -> getMovieDetails(intent)
            MovieDetailsIntent.GetConfigurations -> getConfig()
        }
    }

    private fun getMovieDetails(intent: MovieDetailsIntent.GetMovieDetails) {
        _uiState.update { it.copy(loading = true, error = null, movieDetail = null) }
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(language = intent.language, movieId = intent.movieId)
                .collectLatest { res ->
                    when (res) {
                        is Resource.Failure ->
                            _uiState.update {
                                it.copy(
                                    loading = false,
                                    error = res.error,
                                    movieDetail = null
                                )
                            }

                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    loading = false,
                                    error = null,
                                    movieDetail = res.data
                                )
                            }
                        }
                    }
                }
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