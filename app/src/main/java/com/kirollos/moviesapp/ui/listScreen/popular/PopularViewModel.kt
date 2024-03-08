package com.kirollos.moviesapp.ui.listScreen.popular

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.moviesapp.domain.GetPopularMoviesUseCase
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
class PopularViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(ListUiState())
    var uiState = _uiState.asStateFlow()

    init {
        processIntent(PopularIntent.GetConfigurations)
        processIntent(
            PopularIntent.GetPopularMovies(language = Locale.current.language, page = 1)
        )
    }

    fun processIntent(intent: PopularIntent) {
        when (intent) {
            is PopularIntent.GetPopularMovies -> getPopularMovies(intent)
            PopularIntent.GetConfigurations -> getConfig()
        }
    }

    private fun getPopularMovies(intent: PopularIntent.GetPopularMovies) {
        _uiState.update { it.copy(loading = true, movies = null, error = null) }
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke(intent.language, intent.page).collectLatest { res ->
                when (res) {
                    is Resource.Failure -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                movies = null,
                                error = res.error
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(loading = false, movies = res.data, error = null)
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