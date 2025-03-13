package com.aakash.ecommerce.model

import java.io.Serializable

data class Products(
    val id: String = "",
    val productName: String = "",
    val productPrice: Double = 0.0,
    val productImageUrl: String = ""
) : Serializable
