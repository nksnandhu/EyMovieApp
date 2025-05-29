package com.ey.movieapp.di


import com.ey.movieapp.BuildConfig
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


    private const val BASE_URL = BuildConfig.BASE_URL
  const val IMAGE_URL = BuildConfig.IMAGE_URL
    val BEARER_TOKEN = BuildConfig.BEARER_TOKEN


    @Provides
    fun provideApiService(): ApiService {
        val token = BEARER_TOKEN
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