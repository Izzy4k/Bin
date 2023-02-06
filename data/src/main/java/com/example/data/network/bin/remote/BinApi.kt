package com.example.data.network.bin.remote

import com.example.data.network.bin.dto.BinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("{id}")
    suspend fun getBin(@Path("id") binNumber: String): Response<BinDto>
}