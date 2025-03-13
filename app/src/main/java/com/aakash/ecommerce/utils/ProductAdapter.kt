package com.aakash.ecommerce.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aakash.ecommerce.databinding.CategoryItemBinding
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.views.ProductDetailActivity
import com.bumptech.glide.Glide

class ProductAdapter(
    private val context: Context
) : ListAdapter<Products, ProductAdapter.ProductViewHolder>(ProductDiffUtil()) {
    inner class ProductViewHolder(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgProduct = binding.imgProduct
        val txtProductName = binding.txtProductName
        val txtProductPrice = binding.txtProductPrice
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.txtProductPrice.text = product.productPrice.toString()
        holder.txtProductName.text = product.productName
        Glide.with(holder.itemView.context).load(product.productImageUrl).into(holder.imgProduct)


        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java).also {
                it.putExtra("product",product)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }
}