package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.remote.dto.RegisterResponse
import com.fedenintzel.petshopapp.domain.model.User

fun RegisterResponse.toDomain(): User {
    return User(
        id = id,
        username = "newuser_$id",
        email = email,
        fullName = "$firstName $lastName",
        gender = "unspecified", // datos mockeados
        image = "",
        accessToken = null,
        refreshToken = null
    )
}