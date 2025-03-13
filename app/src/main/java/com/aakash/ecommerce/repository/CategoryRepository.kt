package com.aakash.ecommerce.repository

import androidx.lifecycle.MutableLiveData
import com.aakash.ecommerce.R
import com.aakash.ecommerce.model.Category
import com.aakash.ecommerce.model.Products
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun fetchData(): MutableLiveData<List<Category>> {
        val mutableData = MutableLiveData<List<Category>>()

        val catImages = mapOf(
            "Electronics" to R.drawable.tv,
            "Mobile" to R.drawable.mobile,
            "Cloths" to R.drawable.cloth,
            "Footwear" to R.drawable.footwear,
            "Sofa" to R.drawable.sofa,
            "Kitchen" to R.drawable.kitchen
        )


        firestore.collection("categories").get().addOnSuccessListener { documents ->
            val category = documents.map { document ->
                val imgRes = catImages[document.id] ?: R.drawable.ic_default_product

                Category(catName = document.id, catImage = imgRes)

            }
            mutableData.postValue(category)
//            Log.v("FireStore Data", "FireStore DataList $category")
        }
        return mutableData
    }

    // Fetch the products from collection
    fun fetchProductData(categoryName: String): MutableLiveData<List<Products>> {
        val productList = MutableLiveData<List<Products>>()
        firestore.collection("categories").document(categoryName).collection("products").get()
            .addOnSuccessListener { documents ->
                val products = documents.map { document ->
                    Products(
                        id = document.id.toInt(),
                        productName = document.getString("title") ?: "",
                        productPrice = document.getDouble("price") ?: 0.0,
                        productImageUrl = document.getString("imageUrl") ?: ""
                    )
                }
                productList.postValue(products)
            }
        return productList
    }
}