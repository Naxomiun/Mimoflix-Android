package com.nramos.mimoflix.di

import com.nramos.mimoflix.persistance.AppRoomDataBase
import org.koin.dsl.module

val roomModule = module {
    single { AppRoomDataBase.getInstance(get()) }
    single { get<AppRoomDataBase>().getFavoriteDao() }
}
