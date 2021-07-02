package com.fredericho.moviecatalogue.core.domain.usecase

import com.fredericho.moviecatalogue.core.data.source.MovieRepository
import com.fredericho.moviecatalogue.core.data.source.Resource
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> {
        return movieRepository.getDetailMovie(movieId)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        return movieRepository.setFavoriteMovie(movie, newState)
    }

}