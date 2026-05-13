package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StudentInfoDto(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("studentSensitivityProfiles") val profiles: List<Any> = emptyList()
)

data class StudentInfoVm(
    @SerializedName("studentInfo") val studentInfo: StudentInfoDto,
    @SerializedName("finalScore") val finalScore: Int
)

data class AddStudentRequest(
    @SerializedName("classId") val classId: Int,
    @SerializedName("teachingContextId") val teachingContextId: Int,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("ordinalIndex") val ordinalIndex: Int,
    @SerializedName("medicalNotes") val medicalNotes: String? = null
)

data class AddStudentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("sensitiveLocations") val sensitiveLocations: List<String>
)

data class UpdateStudentRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("medicalNotes") val medicalNotes: String?
)

data class StudentListVm(
    @SerializedName("students") val students: List<StudentSummaryDto>
)

data class StudentSummaryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String? = null
)