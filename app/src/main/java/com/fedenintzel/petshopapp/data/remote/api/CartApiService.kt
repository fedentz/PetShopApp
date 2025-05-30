package com.fedenintzel.petshopapp.data.remote.api

import com.fedenintzel.petshopapp.data.remote.dto.CartDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz que define los endpoints de la API relacionados con el carrito de compras.
 *
 * Esta interfaz es usada por Retrofit para hacer solicitudes HTTP y obtener datos
 * del carrito desde el servidor remoto.
 *
 * Obtiene un carrito de compras específico por su ID.
 *
 *  @param id Identificador del carrito (por defecto el valor es 1)
 *  @return Objeto CartDto con la información del carrito.
 */

interface CartApiService {

    @GET("carts/{id}")
    suspend fun getCartById(@Path("id") id: Int = 1): CartDto
}