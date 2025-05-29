package com.ey.movieapp.di


import com.ey.movieapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
     const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"


    @Provides
    fun provideApiService(): ApiService {
        val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZGYzODIxYWVhMTk2MDY4ZWY4NDE3NDA3OGJiNDdiZiIsIm5iZiI6MTU5Mzc2NTY0My4yMzMsInN1YiI6IjVlZmVlZjBiYmU3ZjM1MDAzMmE2Y2M4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.S1qeof1oEH-cHmpSnPGVb4W61qxkbxcR4Wz0JImrAZs"
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", token)
                    .addHeader("accept", "application/json")
                    .build()
                it.proceed(newRequest)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}