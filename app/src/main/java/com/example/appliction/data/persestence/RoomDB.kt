package com.example.appliction.data.persestence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserInfo::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun UserDao(): UserDao
}