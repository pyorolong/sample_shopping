package com.pyotolong.shopping.data

data class ProductResponse(
    var products: List<Product>,
    var total: Int,
    var skip: Int,
    var limit: Int
)