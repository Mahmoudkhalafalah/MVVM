package com.example.appliction.data.model.productModel

import com.example.appliction.data.model.productModel.Product

data class ProductModel(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)