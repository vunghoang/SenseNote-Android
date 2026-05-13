package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

// 1. Dữ liệu chi tiết học sinh
data class StudentInfoDto(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("studentSensitivityProfiles") val profiles: List<Any> = emptyList()
)

// 2. ViewModel trả về từ GetStudentInfo
data class StudentInfoVm(
    @SerializedName("studentInfo") val studentInfo: StudentInfoDto,
    @SerializedName("finalScore") val finalScore: Int
)

// 3. Request thêm học sinh (Khớp với AddStudentCommand ở Backend)
data class AddStudentRequest(
    @SerializedName("classId") val classId: Int,
    @SerializedName("teachingContextId") val teachingContextId: Int,
    @SerializedName("fullName") val fullName: String,      // Tên đầy đủ
    @SerializedName("displayName") val displayName: String, // Tên hiển thị
    @SerializedName("birthday") val birthday: String?,      // Ngày sinh (định dạng ISO)
    @SerializedName("ordinalIndex") val ordinalIndex: Int,  // Vị trí chỗ ngồi
    @SerializedName("medicalNotes") val medicalNotes: String? = null
)

data class AddStudentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("sensitiveLocations") val sensitiveLocations: List<String>
)

// 4. FIX: Thêm UpdateStudentRequest
data class UpdateStudentRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("medicalNotes") val medicalNotes: String?
)

// 5. FIX: Thêm StudentListVm
// Lưu ý: Nếu Backend trả về danh sách trực tiếp, bạn có thể không cần bọc Vm này.
data class StudentListVm(
    @SerializedName("students") val students: List<StudentSummaryDto>
)

data class StudentSummaryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String? = null
)