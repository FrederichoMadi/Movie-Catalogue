package com.fredericho.moviecatalogue.core.domain.repository

import com.fredericho.moviecatalogue.core.data.source.Resource
import com.fredericho.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie() : Flow<Resource<List<Movie>>>

    fun getDetailMovie(movieId : Int) : Flow<Resource<Movie>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, newState : Boolean)
}