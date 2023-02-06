package com.example.domain.bin.use_case

import com.example.domain.bin.entity.BinHistory
import com.example.domain.bin.repo.BinLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBinUseCase @Inject constructor(
    private val repository: BinLocalRepository
) {
    fun getBin(): Flow<List<BinHistory>> = repository.getBin()
}