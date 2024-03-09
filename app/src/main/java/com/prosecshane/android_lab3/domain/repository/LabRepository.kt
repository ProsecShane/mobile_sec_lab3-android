package com.prosecshane.android_lab3.domain.repository

import com.prosecshane.android_lab3.domain.model.Feedback
import com.prosecshane.android_lab3.domain.network.CallStatus
import kotlinx.coroutines.flow.Flow

interface LabRepository {
    suspend fun getIp(): Flow<CallStatus>
    suspend fun saveFeedback(fb: Feedback)
    suspend fun getFeedback(): List<Feedback>
}
