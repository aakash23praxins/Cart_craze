package com.aakash.ecommerce.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aakash.ecommerce.databinding.ProductItemBinding
import com.aakash.ecommerce.model.Category
import com.aakash.ecommerce.views.CategoryItemActivity
import com.bumptech.glide.Glide

class CategoryAdapter(
    private val context: Context
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    inner class CategoryViewHolder(binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cateImg = binding.imgCategory
        val catName = binding.txtCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.catName.text = category.catName
        Glide.with(holder.itemView.context).load(category.catImage).into(holder.cateImg)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CategoryItemActivity::class.java)
            context.startActivity(intent)
        }
    }


}