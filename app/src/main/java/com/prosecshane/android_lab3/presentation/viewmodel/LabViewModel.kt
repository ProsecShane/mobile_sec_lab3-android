package com.prosecshane.android_lab3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prosecshane.android_lab3.domain.model.Feedback
import com.prosecshane.android_lab3.domain.network.CallStatus
import com.prosecshane.android_lab3.domain.repository.LabRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LabViewModel @Inject constructor(
    private val repository: LabRepository,
): ViewModel() {
    private val _callStatus = MutableStateFlow<CallStatus>(CallStatus.None)
    val callStatus: StateFlow<CallStatus> = _callStatus.asStateFlow()
    private fun setCallStatus(cs: CallStatus) {
        _callStatus.update { cs }
    }

    private val _feedback = MutableStateFlow("")
    val feedback: StateFlow<String> = _feedback.asStateFlow()
    fun setFeedback(fb: String) {
        _feedback.update { fb }
    }

    private val _storedFeedback = MutableStateFlow(listOf<Feedback>())
    val storedFeedback: StateFlow<List<Feedback>> = _storedFeedback.asStateFlow()
    private fun setStoredFeedback(sfb: List<Feedback>) {
        _storedFeedback.update { sfb.sortedBy { it.date } }
    }

    init {
        updateStoredFeedback()
    }

    private fun updateStoredFeedback() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setStoredFeedback(repository.getFeedback())
            }
        }
    }

    fun addFeedback(fb: Feedback) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.saveFeedback(fb)
                setStoredFeedback(repository.getFeedback())
            }
        }
    }

    fun getIp() {
        viewModelScope.launch {
            repository.getIp().collect {
                setCallStatus(it)
            }
        }
    }
}
