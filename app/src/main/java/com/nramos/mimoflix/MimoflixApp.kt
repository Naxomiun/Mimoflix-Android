package com.nramos.mimoflix

import android.app.Application
import com.nramos.mimoflix.di.networkModule
import com.nramos.mimoflix.di.roomModule
import com.nramos.mimoflix.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MimoflixApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MimoflixApp)
            modules(viewModelModule, networkModule, roomModule)
        }
    }

}