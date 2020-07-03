package com.nramos.mimoflix.di

import com.nramos.mimoflix.api.ApiService
import okhttp3.OkHttpClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nramos.mimoflix.api.LocalProvider
import com.nramos.mimoflix.repo.actors.ActorRepository
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.Constants.Companion.API_BASE_URL
import com.nramos.mimoflix.utils.Constants.Companion.CONNECT_TIMEOUT
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        LocalProvider()
    }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(API_BASE_URL)
            .client(get())
            .build()
    }

    single {
        MovieRepository(get(), get())
    }

    single {
        ActorRepository(get())
    }

    factory {
        get<Retrofit>().create(ApiService::class.java)
    }

}