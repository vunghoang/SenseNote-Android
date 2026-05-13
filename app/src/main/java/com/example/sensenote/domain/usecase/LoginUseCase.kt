package com.example.sensenote.domain.usecase

import com.example.sensenote.domain.repository.AuthRepository
import javax.inject.Inject

//class LoginUseCase @Inject constructor(
//    private val repository: AuthRepository
//) {
//    suspend operator fun invoke(email: String, password: String): Result<Unit> {
//        if (email.isBlank() || password.isBlank()) {
//            return Result.failure(Exception("Email và mật khẩu không được để trống."))
//        }
//        return repository.login(email, password)
//    }
//}