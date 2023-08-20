package com.example.appliction.domain.model

import com.example.appliction.data.Repository
import com.example.appliction.data.persestence.UserInfo

class UpdateUserDataUseCase(private val repository: Repository) {
    fun updateUserData(userInfo: UserInfo) {
        repository.updateUserInfo(userInfo)
    }
}