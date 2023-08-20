package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.model.productModel.ProductModel

class GetProductsWithAuthUseCase(private val repository: Repository= Repository()) {

    suspend fun getProductsWithAuth(auth:String):ProductModel{
        return repository.getProductWithAuth(auth)
    }
}