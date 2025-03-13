package com.aakash.ecommerce.utils

import androidx.recyclerview.widget.DiffUtil
import com.aakash.ecommerce.model.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.catName == newItem.catName
    }

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}
