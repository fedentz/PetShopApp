package com.fedenintzel.petshopapp.data.remote.dto

/**
 * Representa la respuesta del endpoint de registro en dummyjson.com.
 * Ejemplo de JSON que devuelve dummyjson.com:
 * {
 *   "id": 101,
 *   "firstName": "Julio",
 *   "lastName": "Cesar",
 *   "email": "julio.cesar@mail.com",
 *   "age" : 30
 * }
 */
data class RegisterResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
)