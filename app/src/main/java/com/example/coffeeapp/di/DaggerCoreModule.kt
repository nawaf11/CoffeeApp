package com.example.coffeeapp.di

import android.content.Context
import com.example.coffeeapp.data.local.database.CoffeeDatabase
import com.example.coffeeapp.data.local.database.dao.CoffeeDao
import com.example.coffeeapp.data.remote.ServerConfig
import com.example.coffeeapp.data.remote.api.CoffeeApiService
import com.example.coffeeapp.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaggerCoreModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoffeeDatabase(@ApplicationContext context : Context) : CoffeeDatabase {
        return CoffeeDatabase.build(context)
    }


    @Provides
    @Singleton
    @JvmStatic
    fun provideCoffeeDao(coffeeDatabase : CoffeeDatabase) : CoffeeDao {
        return coffeeDatabase.coffeeDao()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient() : OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(Constants.NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson() : Gson = Gson()

    @Provides
    @Singleton
    @JvmStatic
    fun provideGsonConverterFactory(gson : Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        converterFactory: GsonConverterFactory) : Retrofit {

        return Retrofit.Builder()
            .baseUrl(ServerConfig.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoffeeApiService(retrofit: Retrofit) : CoffeeApiService {
        return retrofit.create(CoffeeApiService::class.java)
    }

}