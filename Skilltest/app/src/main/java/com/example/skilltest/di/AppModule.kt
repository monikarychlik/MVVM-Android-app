package com.example.skilltest.di

import android.app.Application
import androidx.room.Room
import com.example.skilltest.api.DailyMotionApi
import com.example.skilltest.api.GithubApi
import com.example.skilltest.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ) = Room.databaseBuilder(
        application,
        UserDatabase::class.java,
        "user_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase) =
        database.userDao()

    @Provides
    @DailyMotionNetwork(TypeEnum.URL)
    fun provideDailyMotionUrl() = DailyMotionApi.BASE_URL

    @Provides
    @DailyMotionNetwork(TypeEnum.RETROFIT)
    @Singleton
    fun provideRetrofit(
        @DailyMotionNetwork(TypeEnum.URL) baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @DailyMotionNetwork(TypeEnum.API)
    @Singleton
    fun providesDailyMotionApi(
        @DailyMotionNetwork(TypeEnum.RETROFIT) retrofit: Retrofit
    ): DailyMotionApi =
        retrofit.create(DailyMotionApi::class.java)

    @Provides
    @GithubNetwork(TypeEnum.URL)
    fun provideGithubUrl() = GithubApi.BASE_URL

    @Provides
    @GithubNetwork(TypeEnum.RETROFIT)
    @Singleton
    fun provideRetrofit2(
        @GithubNetwork(TypeEnum.URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @GithubNetwork(TypeEnum.API)
    @Singleton
    fun providesGithubApi(
        @GithubNetwork(TypeEnum.RETROFIT) retrofit: Retrofit
    ): GithubApi =
        retrofit.create(GithubApi::class.java)

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

enum class TypeEnum {
    URL, RETROFIT, API
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DailyMotionNetwork(val value: TypeEnum)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GithubNetwork(val value: TypeEnum)

// for more scopes
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope