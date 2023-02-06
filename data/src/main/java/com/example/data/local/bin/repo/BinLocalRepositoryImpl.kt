package com.example.data.local.bin.repo

import com.example.core.base.fromToList
import com.example.data.local.bin.dao.BinDao
import com.example.data.local.bin.entity.BinHistoryEntity
import com.example.data.local.bin.util.BinHistoryEntityToHistory
import com.example.domain.bin.entity.BinHistory
import com.example.domain.bin.repo.BinLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.List

class BinLocalRepositoryImpl @Inject constructor(
    private val dao: BinDao
) : BinLocalRepository {
    private val binHistoryEntityToHistory = BinHistoryEntityToHistory()
    override suspend fun createBin(bin: String) {
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        dao.createBin(
            BinHistoryEntity(
                bin = bin,
                data = date,
                time = time
            )
        )
    }

    override fun getBin(): Flow<List<BinHistory>> = flow {
        binHistoryEntityToHistory.fromToList(dao.getBin())?.let { emit(it) }
    }
}