package com.aakash.ecommerce.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aakash.ecommerce.databinding.ActivityCategoryItemBinding
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.utils.ProductAdapter
import com.aakash.ecommerce.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryItemBinding

    private lateinit var adapter: ProductAdapter

    private val viewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter(this)

        val catName = intent.getStringExtra("catName") ?: ""
        Log.d("CAT NAME", "Category name : $catName")
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoryRecyclerView.adapter = adapter

        viewModel.fetchProductData(catName).observe(this) { productList ->
            if (productList.isNotEmpty()) {
                adapter.submitList(productList)
            } else {
                Log.e("fetchProductData", "fetchProductData Error...")
            }
        }


    }

    private fun onClickProduct(product: Products) {

    }
}