package com.aakash.ecommerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aakash.ecommerce.model.Products

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(product: Products)

    @Query("select * from cart")
    fun getAllItems(): List<Products>

    @Query("delete from cart where id = :productID")
    suspend fun deleteProductId(productID: String)

    @Query("delete from cart")
    suspend fun clearCart()
}