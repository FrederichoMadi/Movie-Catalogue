package com.fredericho.moviecatalogue

import android.app.Application
import com.fredericho.moviecatalogue.core.di.databaseModule
import com.fredericho.moviecatalogue.core.di.netWorkModule
import com.fredericho.moviecatalogue.core.di.repositoryModule
import com.fredericho.moviecatalogue.di.useCaseModule
import com.fredericho.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    netWorkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}