package com.example.domain.bin.repo

import com.example.domain.bin.entity.BinHistory
import kotlinx.coroutines.flow.Flow

interface BinLocalRepository {
    suspend fun createBin(bin: String)
    fun getBin(): Flow<List<BinHistory>>
}