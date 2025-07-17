package com.fedenintzel.petshopapp.di

import com.fedenintzel.petshopapp.data.local.FavoriteProductDao
import android.content.Context
import androidx.room.Room
import com.fedenintzel.petshopapp.data.local.AppDatabase
import com.fedenintzel.petshopapp.data.local.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_db"
            ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideFavoriteProductDao(db: AppDatabase): FavoriteProductDao =
        db.favoriteProductDao()

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao =
        db.cartDao()
}
