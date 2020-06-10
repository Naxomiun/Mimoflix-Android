package com.nramos.mimoflix.di

import com.nramos.mimoflix.persistance.AppRoomDataBase
import com.nramos.mimoflix.persistance.SearchHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val otherModule = module {
    single { SearchHelper( androidContext() ) }
}
