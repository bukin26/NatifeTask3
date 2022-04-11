package com.gmail.notifytask3.data

import com.gmail.notifytask3.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("api")
    suspend fun fetchUsers(
        @Query("results") results: Int = Constants.USERS_PAGE_SIZE
    ): UsersList

    companion object {

        private const val BASE_URL = "https://randomuser.me/"

        fun create(): UsersService {

            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UsersService::class.java)
        }
    }
}
