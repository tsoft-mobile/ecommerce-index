package com.tsoftmobile.library.index

import android.app.Application
import android.content.Context

class TSoftApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: TSoftApplication

        @JvmStatic
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}