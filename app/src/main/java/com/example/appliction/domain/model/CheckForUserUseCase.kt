package com.example.appliction.domain.model

import com.example.appliction.data.Repository

class CheckForUserUseCase(private val repository: Repository = Repository()) {

    fun findUser(name:String):Boolean{
        return repository.findUser(name)
    }
}