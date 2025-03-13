package com.aakash.ecommerce.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cart")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val productName: String = "",
    val productPrice: Double = 0.0,
    val productImageUrl: String = ""
) : Serializable
