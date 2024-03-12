package com.example.coffeeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoffeeApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}