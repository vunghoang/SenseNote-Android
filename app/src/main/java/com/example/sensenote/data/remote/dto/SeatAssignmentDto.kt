package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SeatAssignmentDto(
    @SerializedName("studentId") val studentId: Int,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("ordinalIndex") val ordinalIndex: Int,
    @SerializedName("sensitiveLocations") val sensitiveLocations: List<String> = emptyList()
)

data class SeatAssignmentsVm(
    @SerializedName("seatAssignments") val seatAssignments: List<SeatAssignmentDto>
)

data class UpdateSeatAssignmentRequest(
    @SerializedName("teachingContextId") val teachingContextId: Int,
    @SerializedName("studentId") val studentId: Int,
    @SerializedName("ordinalIndex") val ordinalIndex: Int
)