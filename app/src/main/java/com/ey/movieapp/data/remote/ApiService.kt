package com.ey.movieapp.data.remote

import com.ey.movieapp.domain.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovieList(@Query("page") page: Int): MovieListResponse
}