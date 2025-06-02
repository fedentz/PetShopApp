package com.fedenintzel.petshopapp.domain.usecase



/**
 * Resultado genérico de una operación, que puede ser:
 *  - Success(data)  → cuando la operación fue correcta
 *  - Error(message) → cuando hubo un problema, con mensaje descriptivo
 *  - Loading       → opcional, si se quiere modelar un estado de carga
 */
sealed class Result<out T> {
    data class Success<out R>(val data: R) : Result<R>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
