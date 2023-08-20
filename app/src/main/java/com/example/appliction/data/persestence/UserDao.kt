package com.example.appliction.data.persestence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserInfo)

    @Delete
    fun deleteUser(user: UserInfo)

    @Update
    fun updateUserData(userInfo: UserInfo)

    @Query("SELECT * FROM USERS_TABLE")
    fun getAllUsers(): List<UserInfo>

    @Query("SELECT * FROM USERS_TABLE WHERE id = :id")
    fun getUserById(id: Int): UserInfo

    @Query("SELECT *FROM USERS_TABLE WHERE name=:name")
    fun findUserByName(name: String): Boolean
}