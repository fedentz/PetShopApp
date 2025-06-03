package com.fedenintzel.petshopapp.domain.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val fullName: String,
    val gender: String,
    val image: String,
    val accessToken: String? = null,
    val refreshToken: String? = null
)