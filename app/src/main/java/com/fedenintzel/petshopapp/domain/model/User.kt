package com.fedenintzel.petshopapp.domain.model

data class User(
    val id: String,
    val username: String = "",
    val email: String,
    val fullName: String = "",
    val image: String = "",

)