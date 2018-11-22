package com.prisyazhnuy.pockemonapp

import android.app.Application

class PockApp : Application() {

    companion object {
        lateinit var instance: PockApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}