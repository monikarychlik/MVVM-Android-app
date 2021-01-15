package com.example.skilltest.api

import com.example.skilltest.data.User
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    suspend fun getUsers(): List<User>
}