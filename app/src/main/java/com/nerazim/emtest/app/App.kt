package com.nerazim.emtest.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App: Application() {

    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()
        container = DataContainer()
        CoroutineScope(Dispatchers.IO).launch {
            container.dataRepository.loadData(applicationContext)
        }
    }
}

fun CreationExtras.jobApplication(): App = (this[APPLICATION_KEY] as App)