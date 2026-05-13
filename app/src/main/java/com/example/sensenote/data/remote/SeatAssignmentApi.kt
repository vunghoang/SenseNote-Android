package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.*
import retrofit2.http.*

interface SeatAssignmentApi {

    @GET("Seats/{teachingContextId}")
    suspend fun getSeatAssignments(
        @Path("teachingContextId") teachingContextId: Int
    ): SeatAssignmentsVm

    @PUT("Seats")
    suspend fun updateSeatAssignment(
        @Body request: UpdateSeatAssignmentRequest
    )
}