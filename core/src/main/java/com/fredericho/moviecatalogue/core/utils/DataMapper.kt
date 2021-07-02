package com.fredericho.moviecatalogue.core.utils

import com.fredericho.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fredericho.moviecatalogue.core.data.source.remote.response.Movies
import com.fredericho.moviecatalogue.core.domain.model.Movie

object DataMapper {

    fun mapResponseToEntities(input: List<Movies>) : List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.date,
                it.poster,
                it.rating,
                "" ,
                false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input : List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                it.id,
                it.title,
                it.overview,
                it.date,
                it.poster,
                it.rating,
                it.genres,
                it.favorite
            )
        }

    fun mapEntityToDomain(input : MovieEntity) : Movie =
        Movie(
            input.id,
            input.title,
            input.overview,
            input.date,
            input.poster,
            input.rating,
            input.genres,
            input.favorite
        )

    fun mapDomainToEntities(input: Movie) =
        MovieEntity(
            input.id,
            input.title,
            input.overview,
            input.date,
            input.poster,
            input.rating,
            input.genres,
            input.favorite
        )

}