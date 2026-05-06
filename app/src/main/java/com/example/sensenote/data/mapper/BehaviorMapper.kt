package com.example.sensenote.data.mapper

import com.example.sensenote.data.remote.dto.BehaviorLogRequest
import com.example.sensenote.domain.model.BehaviorLog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun BehaviorLog.toRequest(): BehaviorLogRequest {
    return BehaviorLogRequest(
        studentId = studentId,
        antecedent = antecedent,
        behavior = behavior,
        consequence = consequence,
        timestamp = timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        location = locationContext
    )
}

fun BehaviorLogRequest.toDomain(id: String): BehaviorLog {
    return BehaviorLog(
        id = id,
        studentId = studentId,
        antecedent = antecedent,
        behavior = behavior,
        consequence = consequence,
        timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        locationContext = location
    )
}