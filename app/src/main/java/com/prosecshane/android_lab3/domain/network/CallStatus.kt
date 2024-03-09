package com.prosecshane.android_lab3.domain.network

sealed class CallStatus {
    data object None: CallStatus()
    data object InProgress: CallStatus()
    data object Error: CallStatus()
    data class Success(val ip: String): CallStatus()
}
