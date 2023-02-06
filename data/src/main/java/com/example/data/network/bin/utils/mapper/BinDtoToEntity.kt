package com.example.data.network.bin.utils.mapper

import com.example.core.base.BaseMapper
import com.example.data.network.bin.dto.BinDto
import com.example.domain.bin.entity.BankEntity
import com.example.domain.bin.entity.BinEntity
import com.example.domain.bin.entity.CountryEntity
import com.example.domain.bin.entity.NumberEntity

class BinDtoToEntity : BaseMapper<BinDto, BinEntity> {

    private val noData = "No data"

    override fun invoke(bin: BinDto): BinEntity {

        val prepaid = if (bin.prepaid == true)
            "Yes"
        else
            "No"

        val luhn = if (bin.number?.luh == true)
            "Yes"
        else
            "No"

        val numberLength: String = if (bin.number?.length != null)
            bin.number.length.toString()
        else
            noData

        val latitude: String = if (bin.country?.latitude != null)
            bin.country.latitude.toString()
        else
            noData

        val longitude: String = if (bin.country?.longitude != null)
            bin.country.longitude.toString()
        else
            noData

        val url: String = if (bin.bank?.url != null)
            bin.bank.url
        else
            noData

        val phone: String = if (bin.bank?.phone != null)
            bin.bank.phone
        else
            noData

        val isCoordinate: Boolean = latitude != noData && longitude != noData

        val isUrl: Boolean = url != noData

        val isPhone: Boolean = phone != noData

        return BinEntity(
            number = NumberEntity(
                numberLength,
                luhn
            ),
            scheme = bin.scheme ?: noData,
            type = bin.type ?: noData,
            brand = bin.brand ?: noData,
            prepaid = prepaid,
            country = CountryEntity(
                emojiName = bin.country?.emoji + " " + bin.country?.name,
                latitude = latitude,
                longitude = longitude,
                isCoordinate = isCoordinate
            ),
            bank = BankEntity(
                name = bin.bank?.name ?: noData,
                url = url,
                phone = phone,
                isUrl = isUrl,
                isPhone = isPhone
            )
        )
    }

}