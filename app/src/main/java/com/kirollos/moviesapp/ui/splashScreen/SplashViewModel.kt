package com.kirollos.moviesapp.ui.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirollos.moviesapp.domain.GetConfigurationUseCase
import com.kirollos.network.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(SplashUiState())
    var uiState = _uiState.asStateFlow()

    private var _sideEffect = Channel<SplashSideEffect>()
    var sideEffect = _sideEffect.receiveAsFlow()

    init {
        processIntent(SplashIntent.DoSplashTask)
    }

    fun processIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.DoSplashTask -> doSplashTask()
        }
    }

    private fun doSplashTask() {
        viewModelScope.launch {
            getConfigurationUseCase.invoke().collectLatest { res ->
                when (res) {
                    is Resource.Failure -> {
                        _uiState.update { it.copy(success = null, error = res.error) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(success = res.data, error = null) }
                        _sideEffect.trySend(SplashSideEffect.Navigate)
                    }
                }
            }
        }
    }
}