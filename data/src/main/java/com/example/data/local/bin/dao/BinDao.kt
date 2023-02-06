package com.example.data.local.bin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.bin.entity.BinHistoryEntity

@Dao
interface BinDao {
    @Query("SELECT * FROM bin_entity")
    suspend fun getBin(): List<BinHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createBin(binHistoryEntity: BinHistoryEntity)
}