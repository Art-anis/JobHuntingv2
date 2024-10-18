package com.nerazim.emtest.app

import android.app.Application
import com.nerazim.emtest.di.appModule
import com.nerazim.emtest.di.dataModule
import com.nerazim.emtest.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}