package com.prisyazhnuy.pockemonapp

import android.app.Application
import com.facebook.stetho.Stetho

class PockApp : Application() {

    companion object {
        lateinit var instance: PockApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }
}