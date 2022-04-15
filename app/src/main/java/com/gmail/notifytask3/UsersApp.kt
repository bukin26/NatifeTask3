package com.gmail.notifytask3

import android.app.Application
import com.gmail.notifytask3.di.DaggerAppComponent

class UsersApp : Application() {
    val appComponent = DaggerAppComponent.factory().create(this)
}