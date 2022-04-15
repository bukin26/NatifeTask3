package com.gmail.notifytask3

import android.app.Application
import com.gmail.notifytask3.di.databaseModule
import com.gmail.notifytask3.di.networkModule
import com.gmail.notifytask3.di.repositoryModule
import com.gmail.notifytask3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UsersApp)
            modules(networkModule, databaseModule, repositoryModule, viewModelModule)
        }
    }
}