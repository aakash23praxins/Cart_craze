package com.aakash.ecommerce.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aakash.ecommerce.databinding.ActivityProductDetailBinding
import com.aakash.ecommerce.model.Products
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as Products

        Log.d("Product Details,", "Product Data $product")
        setData(product)

        binding.btnAddToCart.setOnClickListener {
            addToCartData(product)
        }
    }

    private fun addToCartData(product: Products) {
        Snackbar.make(
            binding.productDetail,
            "Product ${product.productName} is added to cart!!",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setData(product: Products) {
        binding.txtProductNameDetail.text = product.productName
        binding.txtProductPriceDetail.text = "₹".plus(product.productPrice.toString())
        Glide.with(this).load(product.productImageUrl).into(binding.imgProductDetail)
    }
}