package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.persestence.UserInfo

class DeleteUserUseCase(private val repository: Repository= Repository()) {

    fun deleteUser(userInfo: UserInfo){
        repository.deleteUser(userInfo)
    }
}