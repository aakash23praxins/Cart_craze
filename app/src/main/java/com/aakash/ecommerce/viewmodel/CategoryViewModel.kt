package com.aakash.ecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aakash.ecommerce.model.Category
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    fun fetchLiveData(): MutableLiveData<List<Category>> {
        return repository.fetchData()
    }

    fun fetchProductData(categoryName: String): MutableLiveData<List<Products>> {
        return repository.fetchProductData(categoryName)
    }


    fun insertData(products: Products) {
        viewModelScope.launch {
            repository.insertItem(products)
        }
    }

    private val _cartItems = MutableLiveData<List<Products>>()
    val cartItems: MutableLiveData<List<Products>> = _cartItems

    fun getAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getAllItems()
            _cartItems.postValue(items)
        }
    }

    fun deleteProductById(id: String) {
        viewModelScope.launch {
            repository.deleteProductById(id)
            getAllItems()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            getAllItems()
        }
    }

}