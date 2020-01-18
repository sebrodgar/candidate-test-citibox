package com.srg.citibox.common.di.dagger_application

import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import com.srg.citibox.post_list.domain.repository.PostListRepository
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
    fun provideNetworkApi(retrofit: Retrofit): ClientApi = retrofit.create(ClientApi::class.java)


    @Singleton
    @Provides
    fun provideCloudPostListDataSource(api: ClientApi): CloudPostListDataSource =
        CloudPostListDataSource(api)


    @Singleton
    @Provides
    fun providePostListDataRepository(
        api: ClientApi
    ): PostListRepository =
        PostListDataRepository(
            provideCloudPostListDataSource(
                api
            )
        )


    @Singleton
    @Provides
    fun provideCloudPostDetailDataSource(api: ClientApi): CloudPostDetailDataSource =
        CloudPostDetailDataSource(api)


    @Singleton
    @Provides
    fun providePostDetailDataRepository(
        api: ClientApi
    ): PostDetailRepository =
        PostDetailDataRepository(
            provideCloudPostDetailDataSource(
                api
            )
        )

}