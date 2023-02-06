package com.example.bin.ui.fragment.history

import com.example.core.base.BaseViewModel
import com.example.domain.bin.entity.BinHistory
import com.example.domain.bin.use_case.GetBinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getBinUseCase: GetBinUseCase
) : BaseViewModel() {

    fun getBin(): Flow<List<BinHistory>> = getBinUseCase.getBin()
}