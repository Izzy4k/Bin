package com.example.data.local.bin.util

import com.example.core.base.BaseMapper
import com.example.data.local.bin.entity.BinHistoryEntity
import com.example.domain.bin.entity.BinHistory

class BinHistoryEntityToHistory : BaseMapper<BinHistoryEntity, BinHistory> {
    override fun invoke(bin: BinHistoryEntity): BinHistory =
        BinHistory.create(bin = bin.bin, bin.data, bin.time)
}