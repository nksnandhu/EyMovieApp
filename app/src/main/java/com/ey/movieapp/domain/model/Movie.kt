package com.ey.movieapp.domain.model

import com.ey.movieapp.di.AppModule.IMAGE_URL
import com.ey.movieapp.domain.ResultsItem

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: String,
    val posterPath: String
)

fun ResultsItem?.toDomain(): Movie {
    return Movie(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        overview = this?.overview.orEmpty(),
        voteAverage = this?.voteAverage.toString(),
        posterPath = "${IMAGE_URL}${this?.posterPath.orEmpty()}"
    )
}
