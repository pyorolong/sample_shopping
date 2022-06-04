package com.pyotolong.shopping

import android.app.Application
import com.pyotolong.shopping.di.mainModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.android.ext.koin.androidContext


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> {
        return listOf(
            mainModule,
        )
    }
}