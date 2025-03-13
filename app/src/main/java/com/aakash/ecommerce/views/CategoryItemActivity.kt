package com.aakash.ecommerce.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aakash.ecommerce.databinding.ActivityCategoryItemBinding

class CategoryItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}