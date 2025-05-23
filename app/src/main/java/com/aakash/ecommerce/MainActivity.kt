package com.aakash.ecommerce

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.aakash.ecommerce.databinding.ActivityMainBinding
import com.aakash.ecommerce.utils.CategoryAdapter
import com.aakash.ecommerce.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewmodel: CategoryViewModel by viewModels()

    private lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CategoryAdapter(this)
        binding.mainRecyclerView.layoutManager = GridLayoutManager(this, 2)
        viewmodel.fetchLiveData().observe(this) { categoryList ->
            adapter.submitList(categoryList)
            binding.mainRecyclerView.adapter = adapter
        }
    }
}