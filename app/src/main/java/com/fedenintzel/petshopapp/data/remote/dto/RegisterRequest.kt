package com.fedenintzel.petshopapp.data.remote.dto

/**
 * Corresponde al cuerpo (body) que enviamos al endpoint de registro ("/auth/register").
 * Se asume que el back-end espera "username" (o "email"), "password" y "firstName" / "lastName".
 * En dummyjson.com, el endpoint de registro espera:
 * {
 *   "firstName": "Julio",
 *   "lastName": "Cesar",
 *   "username": "julio.cesar",
 *   "email": "julio.cesar@mail.com",
 *   "password": "contraseña123"
 * }
 */
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String
)
