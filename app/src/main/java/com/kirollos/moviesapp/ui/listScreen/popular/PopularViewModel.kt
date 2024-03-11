package com.kirollos.moviesapp.ui.listScreen.popular

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
//    private var _uiState = MutableStateFlow(ListUiState())
//    var uiState = _uiState.asStateFlow()
//
//    init {
//        processIntent(PopularIntent.GetConfigurations)
//        processIntent(PopularIntent.GetPopularMovies)
//    }
//
//    fun processIntent(intent: PopularIntent) {
//        when (intent) {
//            is PopularIntent.GetPopularMovies -> getPopularMovies()
//            PopularIntent.GetConfigurations -> getConfig()
//        }
//    }
//
//    private fun getPopularMovies() {
//        viewModelScope.launch {
//            val moviesFlow = getPopularMoviesUseCase.invoke()
//            _uiState.update { it.copy(moviesFlow = moviesFlow) }
//        }
//    }
//
//    private fun getConfig() {
//        viewModelScope.launch {
//            getConfigurationUseCase.invoke().collectLatest { res ->
//                when (res) {
//                    is Resource.Failure -> {}
//                    is Resource.Success -> _uiState.update { it.copy(config = res.data) }
//                }
//            }
//        }
//    }
}
