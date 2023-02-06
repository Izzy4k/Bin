package com.example.data.local.bin.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_entity")
data class BinHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bin: String,
    val data: String,
    val time: String
)
