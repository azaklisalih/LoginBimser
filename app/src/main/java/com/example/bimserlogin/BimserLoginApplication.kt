package com.example.bimserlogin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BimserLoginApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}