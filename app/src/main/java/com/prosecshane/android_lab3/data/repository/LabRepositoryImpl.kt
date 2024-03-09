package com.prosecshane.android_lab3.data.repository

import com.prosecshane.android_lab3.data.local.LabDao
import com.prosecshane.android_lab3.domain.model.Feedback
import com.prosecshane.android_lab3.domain.network.CallStatus
import com.prosecshane.android_lab3.domain.repository.LabClient
import com.prosecshane.android_lab3.domain.repository.LabRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LabRepositoryImpl @Inject constructor(
    private val client: LabClient,
    private val dao: LabDao,
) : LabRepository {
    override suspend fun getIp(): Flow<CallStatus> = flow {
        emit(CallStatus.InProgress)
        try {
            var result: CallStatus? = null
            client.getIp { result = it }
            emit(result?: CallStatus.Error)
        } catch (e: Exception) {
            emit(CallStatus.Error)
        }
    }

    override suspend fun saveFeedback(fb: Feedback) {
        dao.add(fb)
    }

    override suspend fun getFeedback(): List<Feedback> = dao.get()
}
