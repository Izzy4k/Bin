package com.example.domain.bin.repo

import com.example.core.base.BaseResult
import com.example.domain.bin.entity.BinEntity
import kotlinx.coroutines.flow.Flow

interface BinNetworkRepository {
    suspend fun getDataBin(bin: String): Flow<BaseResult<BinEntity, String>>
}