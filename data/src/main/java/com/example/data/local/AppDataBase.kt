package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.bin.dao.BinDao
import com.example.data.local.bin.entity.BinHistoryEntity


@Database(entities = [BinHistoryEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getBinDao(): BinDao
}