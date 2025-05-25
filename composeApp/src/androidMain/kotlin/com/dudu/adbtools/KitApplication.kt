package com.dudu.adbtools

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlin.properties.Delegates

class KitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context by Delegates.notNull<KitApplication>()
    }
}

val appCtx = KitApplication.context
val Context.dataStore : DataStore<Preferences> by preferencesDataStore("data")