package com.gmail.notifytask3.di

import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideUsersService(): UsersService {

        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersService::class.java)
    }
}