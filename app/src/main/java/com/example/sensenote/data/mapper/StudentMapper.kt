package com.example.sensenote.data.mapper

import com.example.sensenote.data.remote.dto.StudentDto
import com.example.sensenote.domain.model.Student

fun StudentDto.toDomain(): Student {
    return Student(
        id = id,
        name = name,
        dob = dob ?: "01/01/2000",
        status = status ?: "Bình thường",
        isWarning = isWarning ?: false,
        classroomId = classroomId,
        sensoryThresholds = mapOf(
            "Tiếng ồn" to (thresholds?.noise ?: 0),
            "Ánh sáng" to (thresholds?.light ?: 0),
            "Xúc giác" to (thresholds?.touch ?: 0)
        )
    )
}