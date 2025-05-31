package com.fedenintzel.petshopapp.data.remote.dto

/**
 * Representa la respuesta del endpoint de registro en dummyjson.com.
 * Ejemplo de JSON que devuelve dummyjson.com:
 * {
 *   "id": 101,
 *   "firstName": "Julio",
 *   "lastName": "Cesar",
 *   "username": "julio.cesar",
 *   "email": "julio.cesar@mail.com",
 *   "password": "contraseña123",
 *   "token": "eyJhbGciOiJIUzI1..."
 * }
 */
data class RegisterResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val token: String
)
