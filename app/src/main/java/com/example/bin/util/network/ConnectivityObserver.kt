package com.example.bin.util.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>
    fun isInternetAvailable(): Boolean
    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}