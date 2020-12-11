package com.example.english

import android.app.Application

class MyApplication: Application() {

    companion object {
        lateinit var application: Application

        fun getInstance(): Application {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        initApplication(this)
    }

    private fun initApplication(application: Application) {
        MyApplication.application = application
    }
}