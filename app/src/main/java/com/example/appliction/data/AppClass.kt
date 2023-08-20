package com.example.appliction.data

import android.app.Application
import android.content.Context

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}