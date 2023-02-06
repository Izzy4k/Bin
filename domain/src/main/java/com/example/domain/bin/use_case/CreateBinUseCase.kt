package com.example.domain.bin.use_case

import com.example.domain.bin.repo.BinLocalRepository
import javax.inject.Inject

class CreateBinUseCase @Inject constructor(
    private val repository: BinLocalRepository
) {
    suspend fun createBin(bin: String) = repository.createBin(bin)
}