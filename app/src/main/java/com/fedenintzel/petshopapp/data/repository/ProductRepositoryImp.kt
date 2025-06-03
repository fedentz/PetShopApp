package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.ProductRemoteDataSource
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.repository.ProductRepository
import com.fedenintzel.petshopapp.data.mapper.toDomain
import javax.inject.Inject

/**
 * Implementación de ProductRepository que utiliza una fuente remota de datos.
 */
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return remoteDataSource.getAllProducts().map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): Product {
        return remoteDataSource.getProductById(id).toDomain()
    }
}
