package com.prosecshane.android_lab3.data.remote

import com.prosecshane.android_lab3.domain.model.IPResponse
import retrofit2.Response
import retrofit2.http.GET

interface LabApi {
    @GET("json")
    suspend fun getIp(): Response<IPResponse>
}
