package com.example.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*




abstract class BaseViewModel : ViewModel() {
    private val _networkState = MutableStateFlow(false)
    protected val networkState: StateFlow<Boolean> get() = _networkState

    fun setNetworkState(value: Boolean) {
        _networkState.value = value
    }
}