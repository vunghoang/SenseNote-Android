package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.*
import retrofit2.http.*

interface StudentApi {

    // Sửa route thành "Students" khớp với Backend
    @POST("Students")
    suspend fun addStudent(@Body command: AddStudentRequest): AddStudentResponse

    // Backend GetStudentInfo nhận Query params
    @GET("Students")
    suspend fun getStudentInfo(
        @Query("TeachingContextId") contextId: Int,
        @Query("StudentId") studentId: Int
    ): StudentInfoVm

    // Nếu bạn muốn lấy danh sách, hãy kiểm tra lại route ở Backend
    @GET("Students/by-class")
    suspend fun getStudentsByClass(@Query("teachingContextId") classroomId: Int): StudentListVm

    @DELETE("Students/{id}")
    suspend fun deleteStudent(@Path("id") id: Int)

    @PUT("Students")
    suspend fun updateStudent(@Body command: UpdateStudentRequest): List<String>
}