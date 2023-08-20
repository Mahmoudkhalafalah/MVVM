package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.model.productModel.ProductModel

class GetProductUseCase(private val repository: Repository = Repository()) {

    suspend fun getProducts(): ProductModel {
        return repository.getProductData()
    }
}