package com.example.appliction.data

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.appliction.data.model.productModel.LoginRequest
import com.example.appliction.data.model.productModel.ProductModel
import com.example.appliction.data.model.productModel.ProductRequest
import com.example.appliction.data.model.productModel.UserAPILoginInfo
import com.example.appliction.data.network.NetworkService
import com.example.appliction.data.persestence.RoomDB
import com.example.appliction.data.persestence.UserInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository {

    private val context = AppClass.appContext
    private val db =
        Room.databaseBuilder(context, RoomDB::class.java, "MyDB").allowMainThreadQueries().build()
    private val dao = db.UserDao()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitAPI: NetworkService = retrofit.create(NetworkService::class.java)

    private val tokenSharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val tokenEditor: SharedPreferences.Editor? = tokenSharedPreference.edit()


    suspend fun getProductData(): ProductModel {
        return retrofitAPI.getProductData()
    }


    suspend fun addProduct(productRequest: ProductRequest): Any {
        return retrofitAPI.addProduct(productRequest)
    }

    suspend fun updateProduct(
        productRequest: ProductRequest,
        productId: Int,
    ): Any {
        return retrofitAPI.updateProduct(productRequest, productId)
    }

    suspend fun loginRequest(loginRequest: LoginRequest): UserAPILoginInfo {
        return retrofitAPI.loginRequest(loginRequest)
    }

    private suspend fun storeTokenInSharedPref() {
        val token: String = loginRequest(LoginRequest("kminchelle", "0lelplR")).token
        tokenEditor?.apply {
            putString("tokenValue", token)
        }
    }

    suspend fun getTokenFromSharedPref(): String? {
        if (tokenSharedPreference.getString("tokenValue", null) == null) {
            storeTokenInSharedPref()
        }
        return tokenSharedPreference.getString("tokenValue", null)
    }

    suspend fun getProductWithAuth(authorization: String): ProductModel {
        return retrofitAPI.getProductWithAuth(authorization)
    }

    suspend fun deleteProduct(productId: Int): Any {
        return retrofitAPI.deleteProduct(productId)
    }

    fun insertUser(userInfo: UserInfo) {
        dao.insertUser(userInfo)
    }

    fun deleteUser(userInfo: UserInfo) {
        dao.deleteUser(userInfo)
    }

    fun updateUserInfo(userInfo: UserInfo) {
        dao.updateUserData(userInfo)
    }

    fun getALlUsers(): List<UserInfo> {
        return dao.getAllUsers()
    }

    fun getUserById(id: Int): UserInfo {
        return dao.getUserById(id)
    }

    fun findUser(name: String): Boolean {
        return dao.findUserByName(name)
    }
}