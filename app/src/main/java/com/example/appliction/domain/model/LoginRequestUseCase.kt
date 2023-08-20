package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.model.productModel.LoginRequest
import com.example.appliction.data.model.productModel.UserAPILoginInfo

class LoginRequestUseCase(private val repository: Repository= Repository()) {

    suspend fun loginRequest(loginRequest: LoginRequest):UserAPILoginInfo{
        return repository.loginRequest(loginRequest)
    }
}