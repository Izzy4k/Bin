package com.example.domain.bin.entity

data class BinEntity(
    val number: NumberEntity,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val country: CountryEntity,
    val bank: BankEntity
)

data class NumberEntity(
    val length: String,
    val luh: String
)

data class CountryEntity(
    val emojiName: String,
    val latitude: String,
    val longitude: String,
    val isCoordinate: Boolean
)

data class BankEntity(
    val name: String,
    val url: String,
    val phone: String,
    val isUrl: Boolean,
    val isPhone: Boolean
)
