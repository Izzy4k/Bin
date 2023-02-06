package com.example.data.network.bin.module

import com.example.data.network.bin.repo.BinNetworkRepositoryImpl
import com.example.domain.bin.repo.BinNetworkRepository
import com.example.domain.bin.use_case.GetDataBinUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BinNetworkModule {

    @Binds
    fun bindsBinNetworkRepository(
        binNetworkRepositoryImpl: BinNetworkRepositoryImpl
    ): BinNetworkRepository

}