package com.aakash.ecommerce.di

import android.content.Context
import com.aakash.ecommerce.database.CartDao
import com.aakash.ecommerce.database.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CartDatabase {
        return CartDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(cartDatabase: CartDatabase): CartDao {
        return cartDatabase.cartDao()
    }
}