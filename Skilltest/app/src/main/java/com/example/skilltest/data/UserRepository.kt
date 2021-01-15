package com.example.skilltest.data

import android.util.Log
import com.example.skilltest.api.DailyMotionApi
import com.example.skilltest.api.GithubApi
import com.example.skilltest.di.DailyMotionNetwork
import com.example.skilltest.di.GithubNetwork
import com.example.skilltest.di.TypeEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @DailyMotionNetwork(TypeEnum.API) private val dailyMotionApi: DailyMotionApi,
    @GithubNetwork(TypeEnum.API) private val githubApi: GithubApi,
    private val userDao: UserDao,
) {

    fun getAll(): Flow<List<User>> {
        return userDao.getAll()
    }

    suspend fun loadUsers() {
        try {
            val dailyMotionUsersResponse = dailyMotionApi.getUsers()
            dailyMotionUsersResponse.list.forEach { it.sourceUrl = DailyMotionApi.BASE_URL }
            userDao.insert(dailyMotionUsersResponse.list)

            val githubUsersResponse = githubApi.getUsers()
            githubUsersResponse.forEach { it.sourceUrl = GithubApi.BASE_URL }
            userDao.insert(githubUsersResponse)
        } catch (exception: Exception) {
            Log.e("loadUsers: ", exception.message, exception.cause)
            // Handling errors
        }
    }
}