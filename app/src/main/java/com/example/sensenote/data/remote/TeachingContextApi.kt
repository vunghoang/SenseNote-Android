package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.CreateTeachingContextRequest
import com.example.sensenote.data.remote.dto.TeachingContextsVm
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TeachingContextApi {
    @GET("TeachingContexts")
    suspend fun getTeachingContexts(): TeachingContextsVm

    @POST("TeachingContexts")
    suspend fun createTeachingContext(@Body request: CreateTeachingContextRequest): Int
}