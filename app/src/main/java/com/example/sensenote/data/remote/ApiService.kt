package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.AuthResponse
import com.example.sensenote.data.remote.dto.BehaviorLogRequest
import com.example.sensenote.data.remote.dto.ClassDto
import com.example.sensenote.data.remote.dto.LoginRequest
import com.example.sensenote.data.remote.dto.StudentDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // --- Xác thực ---
    @POST("Auth/Login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("Auth/Register")
    suspend fun register(@Body request: LoginRequest): Response<Unit>

    // --- Học sinh ---
    @GET("students/class/{classId}")
    suspend fun getStudentsByClass(@Path("classId") classId: String): List<StudentDto>

    @GET("students/{id}")
    suspend fun getStudentInfo(@Path("id") studentId: String): StudentDto

    // --- Hành vi ---
    @POST("behavior-logs")
    suspend fun logBehavior(@Body request: BehaviorLogRequest): Response<Unit>

    @GET("behavior-logs/student/{studentId}")
    suspend fun getBehaviorHistory(@Path("studentId") studentId: String): List<BehaviorLogRequest>

    // --- Sơ đồ lớp ---
    @GET("teaching-contexts/{id}")
    suspend fun getTeachingContext(@Path("id") contextId: String): Response<Unit> // Cần DTO cho Context

    @GET("students/class/{classId}")
    suspend fun getAllClasses(): Response<List<ClassDto>>
}