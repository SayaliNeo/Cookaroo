package com.app.Cookaroo

import android.app.Application
import com.app.common.di.appModule
import com.app.presentation.di.viewModelModule
import com.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CookarooApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CookarooApplication)
            modules(appModule, viewModelModule,domainModule)
        }
    }
}