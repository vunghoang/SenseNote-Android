package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.domain.model.BehaviorLog
import com.example.sensenote.domain.usecase.LogBehaviorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BehaviorViewModel @Inject constructor(
    private val logBehaviorUseCase: LogBehaviorUseCase
) : ViewModel() {

    fun saveLog(studentId: String, a: String, b: String, c: String) {
        viewModelScope.launch {
            val newLog = BehaviorLog(
                id = "",
                studentId = studentId,
                antecedent = a,
                behavior = b,
                consequence = c,
                timestamp = LocalDateTime.now(),
                locationContext = null
            )
            logBehaviorUseCase(newLog)
        }
    }
}