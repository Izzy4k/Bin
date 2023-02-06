package com.example.data.local.bin.module

import com.example.data.local.bin.repo.BinLocalRepositoryImpl
import com.example.domain.bin.repo.BinLocalRepository
import com.example.domain.bin.use_case.CreateBinUseCase
import com.example.domain.bin.use_case.GetBinUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BinLocalModule {

    @Binds
    fun bindsBinLocalRepository(
        binLocalRepositoryImpl: BinLocalRepositoryImpl
    ): BinLocalRepository

}