package com.ey.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ey.movieapp.data.remote.ApiService
import com.ey.movieapp.data.remote.MoviePagingSource
import com.ey.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviePagingSource(apiService) }
        ).flow
    }
}