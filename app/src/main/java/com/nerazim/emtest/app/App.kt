package com.nerazim.emtest.app

import android.app.Application
import com.nerazim.emtest.di.appModule
import com.nerazim.emtest.di.dataModule
import com.nerazim.emtest.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

//класс приложения
class App: Application() {

    //при запуске приложения
    override fun onCreate() {
        super.onCreate()

        //запускаем koin
        startKoin {
            //запускаем логгер
            androidLogger(Level.DEBUG)
            //передаем контекст
            androidContext(applicationContext)
            //список модулей
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}