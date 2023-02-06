package com.example.bin.ui.fragment.home

import androidx.lifecycle.viewModelScope
import com.example.bin.util.ext.MutableLiveEvent
import com.example.bin.util.ext.publishEvent
import com.example.bin.util.ext.share
import com.example.core.base.BaseResult
import com.example.core.base.BaseViewModel
import com.example.core.base.Empty
import com.example.domain.bin.entity.BinEntity
import com.example.domain.bin.use_case.CreateBinUseCase
import com.example.domain.bin.use_case.GetDataBinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataBinUseCase: GetDataBinUseCase,
    private val createBinUseCase: CreateBinUseCase
) : BaseViewModel() {
    private val _binResult = MutableStateFlow<BaseResult<BinEntity, String>>(Empty)
    val binResult: StateFlow<BaseResult<BinEntity, String>> get() = _binResult

    private val _errorEvents = MutableLiveEvent<String>()
    val errorEvents = _errorEvents.share()

    fun setError(message: String) {
        _errorEvents.publishEvent(message)
    }

    fun getBin(binNumber: String) {
        if (!networkState.value) {
            setError("No internet connection")
            return
        }
        if (binNumber.isEmpty()) {
            setError("Empty")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            createBinUseCase.createBin(binNumber)
            getDataBinUseCase.getDataBin(binNumber).collect {
                _binResult.value = it
            }
        }
    }
}