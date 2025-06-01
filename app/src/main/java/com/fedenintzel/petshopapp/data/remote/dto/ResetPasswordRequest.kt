package com.fedenintzel.petshopapp.data.remote.dto

data class ResetPasswordRequest(
    val email: String,
    val newPassword: String
)
