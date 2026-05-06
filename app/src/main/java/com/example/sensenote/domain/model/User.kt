package com.example.sensenote.domain.model

data class User(
    val id: String,
    val email: String,
    val fullName: String,
    val schoolName: String?
)