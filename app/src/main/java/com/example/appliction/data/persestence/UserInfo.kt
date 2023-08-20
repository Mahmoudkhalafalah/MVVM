package com.example.appliction.data.persestence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("USERS_TABLE")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val password: String,
    val gender: String = "Male",
    val birthDate: String,
    val email: String,
)