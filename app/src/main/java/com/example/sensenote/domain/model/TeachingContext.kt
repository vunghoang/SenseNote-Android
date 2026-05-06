package com.example.sensenote.domain.model

data class TeachingContext(
    val id: String,
    val name: String,
    val rows: Int,
    val columns: Int,
    val sensoryZones: List<SensoryZone>
)

data class SensoryZone(
    val x: Int,
    val y: Int,
    val type: String,
    val intensity: Int
)