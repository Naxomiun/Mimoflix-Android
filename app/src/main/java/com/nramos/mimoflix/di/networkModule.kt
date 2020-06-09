package com.nramos.mimoflix.di

import com.nramos.mimoflix.api.ApiService
import okhttp3.OkHttpClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nramos.mimoflix.api.RecommendedProvider
import com.nramos.mimoflix.repo.MoviesRepository
import com.nramos.mimoflix.utils.Constants.Companion.API_BASE_URL
import com.nramos.mimoflix.utils.Constants.Companion.CONNECT_TIMEOUT
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        RecommendedProvider()
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
        MoviesRepository(get(), get(), get())
    }

    factory {
        get<Retrofit>().create(ApiService::class.java)
    }

}