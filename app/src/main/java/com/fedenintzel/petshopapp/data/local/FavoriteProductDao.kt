package com.fedenintzel.petshopapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: FavoriteProductEntity)

    @Delete
    suspend fun delete(product: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_products WHERE id = :id")
    suspend fun getById(id: Int): FavoriteProductEntity?

    @Query("SELECT * FROM favorite_products")
    fun getAll(): Flow<List<FavoriteProductEntity>>
}