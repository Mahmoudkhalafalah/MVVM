package com.example.appliction.data.network

import com.example.appliction.data.model.productModel.LoginRequest
import com.example.appliction.data.model.productModel.ProductModel
import com.example.appliction.data.model.productModel.ProductRequest
import com.example.appliction.data.model.productModel.UserAPILoginInfo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NetworkService {

    @GET("products")
    suspend fun getProductData(): ProductModel

    @POST("products/add")
    suspend fun addProduct(@Body productRequest: ProductRequest): Any

    @PUT("products/{id}")
    suspend fun updateProduct(@Body productRequest: ProductRequest, @Path("id") productId: Int): Any

    @POST("auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest): UserAPILoginInfo

    @GET("auth/products")
    suspend fun getProductWithAuth(@Header("Authorization") authorization: String): ProductModel

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int): Any

}