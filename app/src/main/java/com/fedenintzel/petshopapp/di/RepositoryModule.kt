package com.fedenintzel.petshopapp.di

import com.fedenintzel.petshopapp.data.local.FavoritesRepositoryImpl
import com.fedenintzel.petshopapp.data.repository.CartRepositoryImpl
import com.fedenintzel.petshopapp.data.repository.ProductRepositoryImpl
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.fedenintzel.petshopapp.domain.repository.FavoritesRepository
import com.fedenintzel.petshopapp.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Este módulo vincula la interfaz CartRepository con su implementación CartRepositoryImpl
 * para que Hilt sepa cómo inyectarla.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindProductsRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        impl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
