package com.koti.cake

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CakeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CakeApp)
            modules(appModules())
        }
    }
}
