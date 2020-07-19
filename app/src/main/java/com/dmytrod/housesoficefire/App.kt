package com.dmytrod.housesoficefire

import android.app.Application
import com.dmytrod.housesoficefire.data.dataModule
import com.dmytrod.housesoficefire.domain.domainModule
import com.dmytrod.housesoficefire.presentation.viewmodel.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}