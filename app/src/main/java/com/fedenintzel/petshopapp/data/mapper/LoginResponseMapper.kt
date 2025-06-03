package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.remote.dto.LoginResponse
import com.fedenintzel.petshopapp.domain.model.User

fun LoginResponse.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email,
        fullName = "$firstName $lastName",
        gender = gender,
        image = image,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}