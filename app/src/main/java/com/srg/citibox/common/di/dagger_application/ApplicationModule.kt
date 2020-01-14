package com.srg.citibox.common.di.dagger_application

import android.app.Application
import com.srg.citibox.common.data.network.retrofit.CitiboxApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

@Module
class ApplicationModule {

    private val TIMEOUT_IN_SECONDS = 60L

    @Singleton
    @Provides
    fun provideApiURLRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideCitiboxApi(retrofit: Retrofit): CitiboxApi = retrofit.create(CitiboxApi::class.java)

    /*@Singleton
    @Provides
    fun providePostRepository()*/


}