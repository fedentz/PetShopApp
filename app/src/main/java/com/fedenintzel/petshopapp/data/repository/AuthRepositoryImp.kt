package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.mapper.toDomain
import com.fedenintzel.petshopapp.data.remote.AuthRemoteDataSource
import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String): User {
        val response = remoteDataSource.login(username, password)
        return response.toDomain()
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): User {
        val response = remoteDataSource.register(firstName, lastName, username, email, password)
        return response.toDomain()
    }

}