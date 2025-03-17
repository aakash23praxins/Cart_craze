package com.aakash.ecommerce.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aakash.ecommerce.R
import com.aakash.ecommerce.databinding.ActivityCartBinding
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.utils.CartAdapter
import com.aakash.ecommerce.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    private val viewModel: CategoryViewModel by viewModels()


    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CartAdapter(this, viewmodel = viewModel, onProductDeleteClick = {})

        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch {
            viewModel.cartItems.observe(this@CartActivity) { items ->
                updateUI(items)
            }
        }

        binding.btnClearCart.setOnClickListener {
            viewModel.clearCart()
        }
        viewModel.getAllItems()
    }

    private fun updateUI(items: List<Products>) {
        adapter.submitList(items)
        val totalPrice = items.map { product ->
            product.productPrice
        }
        println(totalPrice.sum())
        binding.txtTotalPrice.text = getString(R.string.total).plus(" ").plus(totalPrice.sum().toString())
        adapter.notifyDataSetChanged()
    }

}