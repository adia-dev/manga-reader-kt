package com.adia.dev.playground.application

import android.app.Application
import com.adia.dev.playground.di.modules.appModule
import com.adia.dev.playground.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlaygroundApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PlaygroundApp)
            modules(listOf(appModule, networkModule))
        }
    }
}