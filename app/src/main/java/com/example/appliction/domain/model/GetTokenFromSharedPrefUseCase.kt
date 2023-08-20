package com.example.appliction.domain.model

import com.example.appliction.data.Repository

class GetTokenFromSharedPrefUseCase(private val repository: Repository= Repository()) {

    suspend fun getToken():String?{
        return repository.getTokenFromSharedPref()
    }
}