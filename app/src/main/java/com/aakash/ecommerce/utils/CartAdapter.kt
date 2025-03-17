package com.aakash.ecommerce.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aakash.ecommerce.databinding.CartItemBinding
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.viewmodel.CategoryViewModel
import com.bumptech.glide.Glide

class CartAdapter(
    private val context: Context,
    private val onProductDeleteClick: (Products) -> Unit,
    private val viewmodel: CategoryViewModel
) : ListAdapter<Products, CartAdapter.CartViewHolder>(ProductDiffUtil()) {
    inner class CartViewHolder(binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgProduct = binding.imgCartPr
        val txtProductName = binding.txtPrName
        val txtProductPrice = binding.txtPrPrice
        val imgDeleteProduct = binding.imgDeleteProduct
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)

        holder.txtProductPrice.text = product.productPrice.toString()
        holder.txtProductName.text = product.productName
        Glide.with(holder.itemView.context).load(product.productImageUrl).into(holder.imgProduct)

        holder.imgDeleteProduct.setOnClickListener {
            viewmodel.deleteProductById(product.id.toString())
            Toast.makeText(context, "Deleted Successfully!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }
}