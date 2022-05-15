package com.interactivestandard.test

import android.app.Application
import com.interactivestandard.test.di.appModule
import com.interactivestandard.test.di.detailsFragmentModule
import com.interactivestandard.test.di.errorFragmentModule
import com.interactivestandard.test.di.mainFragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                mainFragmentModule,
                detailsFragmentModule,
                errorFragmentModule
            )
        }
    }
}