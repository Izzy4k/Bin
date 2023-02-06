package com.example.domain.bin.entity

data class BinHistory(
    val bin: String,
    val data: String,
    val time: String
) {
    companion object {
        fun create(
            bin: String,
            data: String,
            time: String
        ): BinHistory = BinHistory(bin, data, time)
    }
}
