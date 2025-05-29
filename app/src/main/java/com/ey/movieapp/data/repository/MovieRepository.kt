package com.ey.movieapp.data.repository

import androidx.paging.PagingData
import com.ey.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}