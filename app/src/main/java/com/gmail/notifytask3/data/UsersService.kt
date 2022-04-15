package com.gmail.notifytask3.data

import com.gmail.notifytask3.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("api")
    suspend fun fetchUsers(
        @Query("results") results: Int = Constants.USERS_PAGE_SIZE
    ): UsersList
}
