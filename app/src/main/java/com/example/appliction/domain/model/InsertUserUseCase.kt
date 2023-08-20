package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.persestence.UserInfo

class InsertUserUseCase(private val repository: Repository = Repository()) {

    fun insertUser(userInfo: UserInfo) {
        repository.insertUser(userInfo)
    }

}