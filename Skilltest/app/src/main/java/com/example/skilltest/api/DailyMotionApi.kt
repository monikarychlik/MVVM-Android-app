package com.example.skilltest.api

import retrofit2.http.GET


interface DailyMotionApi {

    companion object {
        const val BASE_URL = "https://api.dailymotion.com/"
    }

    @GET("users?fields=avatar_190_url%2Cscreenname")
    suspend fun getUsers(): DailyMotionUsersResponse
}