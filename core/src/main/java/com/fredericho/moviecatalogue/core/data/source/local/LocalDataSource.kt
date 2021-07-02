package com.fredericho.moviecatalogue.core.data.source.local

import com.fredericho.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fredericho.moviecatalogue.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDao: MovieDao) {

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getDetailMovies(movieId : Int) : Flow<MovieEntity> = movieDao.getDetailMovie(movieId)

    fun getListFavoriteMovie() : Flow<List<MovieEntity>> = movieDao.getListFavoriteMovie()

    suspend fun insertPlayer(movie : List<MovieEntity>) = movieDao.insertMovie(movie)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState : Boolean) {
        movieEntity.favorite = newState
        movieDao.updateMovie(movieEntity)

    }
}