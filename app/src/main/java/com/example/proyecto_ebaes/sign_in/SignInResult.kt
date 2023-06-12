package com.example.proyecto_ebaes.sign_in

data class SignInResult(
    val data: User?,
    val errorMessage: String?
)

data class User(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)