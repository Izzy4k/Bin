package com.example.domain.bin.use_case

import com.example.core.base.BaseResult
import com.example.domain.bin.entity.BinEntity
import com.example.domain.bin.repo.BinNetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataBinUseCase @Inject constructor(
    private val repository: BinNetworkRepository
) {
    suspend fun getDataBin(bin: String): Flow<BaseResult<BinEntity, String>> =
        repository.getDataBin(
            bin
        )
}