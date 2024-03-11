package com.kirollos.moviesapp.ui.listScreen.nowPlaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.moviesapp.domain.GetNowPlayingMoviesUseCase
import com.kirollos.moviesapp.ui.utils.ListUiState
import com.kirollos.network.data.remote.Resource
import com.kirollos.network.domain.model.Result
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
        processIntent(NowPlayingIntent.GetNowPlayingMovies(page = 1))
    }

    fun processIntent(intent: NowPlayingIntent) {
        when (intent) {
            is NowPlayingIntent.GetNowPlayingMovies -> {
                _uiState.update { it.copy(loading = true) }
                getNowPlayingMovies(intent.page)
            }

            is NowPlayingIntent.LoadMoreMovies -> {
                _uiState.update { it.copy(loadingItem = true) }
                getNowPlayingMovies(intent.page)
            }

            NowPlayingIntent.GetConfigurations -> getConfig()
        }
    }

    private fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase.invoke(page)
                .collectLatest { res ->
                    when (res) {
                        is Resource.Failure -> _uiState.update {
                            it.copy(loading = false, loadingItem = false, error = res.error)
                        }

                        is Resource.Success -> {
                            val newMoviesList = mutableListOf<Result?>()
                            newMoviesList.apply {
                                val oldMovie = _uiState.value.movie
                                if (oldMovie != null && oldMovie.resultList?.isNotEmpty() == true)
                                    addAll(_uiState.value.movie?.resultList!!) //Old Movies
                                addAll(res.data.resultList!!)//New Movies
                            }
                            res.data.resultList = newMoviesList
                            _uiState.update {
                                it.copy(loading = false, loadingItem = false, movie = res.data)
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