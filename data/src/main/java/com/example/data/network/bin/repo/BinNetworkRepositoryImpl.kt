package com.example.data.network.bin.repo

import com.example.core.base.BaseResult
import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.data.network.bin.remote.BinApi
import com.example.data.network.bin.utils.mapper.BinDtoToEntity
import com.example.domain.bin.entity.BinEntity
import com.example.domain.bin.repo.BinNetworkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class BinNetworkRepositoryImpl @Inject constructor(
    private val api: BinApi
) : BinNetworkRepository {

    private val binDtoToEntity = BinDtoToEntity()
    private val noData = "No data"

    override suspend fun getDataBin(bin: String): Flow<BaseResult<BinEntity, String>> =
        callbackFlow {
            try {
                send(PendingResult)
                val result = api.getBin(bin)
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        trySend(SuccessResult(binDtoToEntity.invoke(result.body()!!)))
                    } else {
                        trySend(ErrorResult(noData))
                    }
                }
            } catch (e: Exception) {
                trySend(ErrorResult(e.message.toString()))
            }
            awaitClose {

            }
        }
}