package com.kirollos.moviesapp.ui.listScreen.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.moviesapp.domain.GetUpcomingMoviesUseCase
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
class UpcomingViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(ListUiState())
    var uiState = _uiState.asStateFlow()

    init {
        processIntent(UpcomingIntent.GetConfigurations)
        processIntent(UpcomingIntent.GetUpcomingMovies(page = 1))
    }

    fun processIntent(intent: UpcomingIntent) {
        when (intent) {
            is UpcomingIntent.GetUpcomingMovies -> {
                _uiState.update { it.copy(loading = true) }
                getUpcomingMovies(intent.page)
            }

            is UpcomingIntent.LoadMoreMovies -> {
                _uiState.update { it.copy(loadingItem = true) }
                getUpcomingMovies(intent.page)
            }

            UpcomingIntent.GetConfigurations -> getConfig()
        }
    }

    private fun getUpcomingMovies(page: Int) {
        viewModelScope.launch {
            getUpcomingMoviesUseCase.invoke(page)
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