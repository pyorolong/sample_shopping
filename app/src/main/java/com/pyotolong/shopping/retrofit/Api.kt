package com.pyotolong.shopping.retrofit

import com.pyotolong.shopping.data.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/products")
    fun getProducts(@Query("limit") limit: Int = 20): Call<ProductResponse>

    @GET("/products/categories")
    fun getCategories(): Call<Array<String>>

    @GET("/products/category/{category}")
    fun getProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int = 20
    ): Call<ProductResponse>

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}