package com.fedenintzel.petshopapp.di


import android.content.Context
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.data.remote.api.CartApiService
import com.fedenintzel.petshopapp.data.remote.api.ProductApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Módulo de Hilt encargado de proveer las dependencias relacionadas con la red.
 *
 * Incluye:
 * - Configuración de Retrofit
 * - Cliente HTTP
 * - Servicios API: Cart, Auth, Product
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

         /**
         * Provee una instancia base de Retrofit con la URL de la API dummy.
         */
    @Provides
    @Singleton
    fun provideRetrofit(
            @ApplicationContext context: Context
    ): Retrofit {
             val baseUrl = context.getString(R.string.base_url)

             return Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
         }

    /**
     * Provee una instancia de CartApiService, que será usada para acceder
     * a los endpoints de carrito de la API.
     */
    @Provides
    @Singleton
    fun provideCartApiService(retrofit: Retrofit): CartApiService =
        retrofit.create(CartApiService::class.java)

    /**
     *  Ya no la necesitamos!!
     * Provee una instancia de AuthApiService
     */
//    @Provides
//    @Singleton
//    fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
//        retrofit.create(AuthApiService::class.java)

    /**
     * Provee una instancia de ProductApiService.
     */
    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApiService =
        retrofit.create(ProductApiService::class.java)

    /**
     * Provee una instancia de FirebaseAuth
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provee una instancia de Firebase DB
     */
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()


}