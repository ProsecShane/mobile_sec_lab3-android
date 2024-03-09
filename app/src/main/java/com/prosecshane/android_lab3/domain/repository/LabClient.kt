package com.prosecshane.android_lab3.domain.repository

import com.prosecshane.android_lab3.domain.network.CallStatus

interface LabClient {
    suspend fun getIp(callback: (CallStatus) -> Unit)
}
