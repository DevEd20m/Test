package com.faztbit.alwaopportunity

import android.app.Application
import com.faztbit.alwaopportunity.di.appModule
import com.faztbit.alwaopportunity.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppBaseApplication)
            modules(appModule,viewModelModule)
        }
    }
}