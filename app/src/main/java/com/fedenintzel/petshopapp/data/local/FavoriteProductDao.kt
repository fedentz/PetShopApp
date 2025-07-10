package com.fedenintzel.petshopapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: FavoriteProductEntity)

    @Delete
    suspend fun delete(product: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_products WHERE id = :id AND userId = :userId")
    suspend fun getById(id: Int, userId: String): FavoriteProductEntity?

    @Query("SELECT * FROM favorite_products WHERE userId = :userId")
    fun getAll(userId: String): Flow<List<FavoriteProductEntity>>

    @Query("DELETE FROM favorite_products WHERE userId = :userId")
    suspend fun clearFavorites(userId: String)
}