package com.pyotolong.shopping.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pyotolong.shopping.common.Constants.CATEGORY_ALL
import com.pyotolong.shopping.data.Product
import com.pyotolong.shopping.data.ProductResponse
import com.pyotolong.shopping.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel : ViewModel() {
    private var _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> get() = _products

    private val retrofitResult = object : Callback<ProductResponse> {
        override fun onResponse(
            call: Call<ProductResponse>,
            response: Response<ProductResponse>
        ) {
            response.body()?.let {
                _products.postValue(it.products)
            }
        }

        override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            Log.e("", "error occurred : ${t.message}")
        }

    }

    fun getProductsList(category: String) {
        if (category == CATEGORY_ALL) {
            RetrofitBuilder.api.getProducts()
                .enqueue(retrofitResult)
        } else {
            RetrofitBuilder.api.getProductsByCategory(category)
                .enqueue(retrofitResult)
        }
    }
}