package com.ey.movieapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.ey.movieapp.domain.model.Movie
import com.ey.movieapp.domain.model.toDomain

class MoviePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            Log.d("MoviePagingSource", "Loading page: $page")
            val response = apiService.getMovieList(page)

            val movies = response.results?.mapNotNull { it?.toDomain() } ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= (response.totalPages ?: page)) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}