package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import javax.inject.Inject

/**
 * UseCase para obtener el carrito desde el repositorio.
 * Este caso de uso abstrae la lógica de obtención del carrito, permitiendo reutilización
 * y facilitando testeo en la capa de presentación.
 *
 * Le pasamos id = 1 para que funcione con la API
 */

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Cart {
        return repository.getCartById(1)
    }
}