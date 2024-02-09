package com.adia.dev.playground.di.modules

import android.content.Context
import com.adia.dev.playground.R
import com.adia.dev.playground.network.MangaApi
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.ext.koin.androidContext

val networkModule = module {
    single { provideRetrofit(androidContext()) }
    single { provideMangaApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    val baseUrl = context.getString(R.string.base_url)
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp3.OkHttpClient.Builder().addInterceptor(interceptor).build())
        .build()
}

fun provideMangaApi(retrofit: Retrofit): MangaApi = retrofit.create(MangaApi::class.java)
