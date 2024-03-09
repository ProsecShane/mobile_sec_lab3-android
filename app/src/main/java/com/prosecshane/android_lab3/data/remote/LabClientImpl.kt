package com.prosecshane.android_lab3.data.remote

import com.prosecshane.android_lab3.domain.network.CallStatus
import com.prosecshane.android_lab3.domain.repository.LabClient
import javax.inject.Inject

class LabClientImpl @Inject constructor(
    private val api: LabApi,
) : LabClient {
    override suspend fun getIp(callback: (CallStatus) -> Unit) {
        try {
            val response = api.getIp()
            val ip = response.body()!!.ip
            callback(CallStatus.Success(ip))
        } catch (e: Exception) {
            callback(CallStatus.Error)
        }
    }
}
