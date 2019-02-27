package com.sittipong.scbtestandroid.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



class SCBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("scb.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}