package com.aakash.ecommerce.utils

import androidx.recyclerview.widget.DiffUtil
import com.aakash.ecommerce.model.Products

class ProductDiffUtil : DiffUtil.ItemCallback<Products>() {
    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.productName == newItem.productName
    }

}
