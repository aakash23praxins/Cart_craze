package com.aakash.ecommerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aakash.ecommerce.model.Category
import com.aakash.ecommerce.model.Products
import com.aakash.ecommerce.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
}