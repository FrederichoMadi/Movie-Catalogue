package com.fredericho.moviecatalogue.core.data.source

import com.fredericho.moviecatalogue.core.data.source.local.LocalDataSource
import com.fredericho.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fredericho.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.fredericho.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.fredericho.moviecatalogue.core.data.source.remote.response.DetailMovies
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.domain.repository.IMovieRepository
import com.fredericho.moviecatalogue.core.utils.AppExecutor
import com.fredericho.moviecatalogue.core.utils.DataMapper
import com.fredericho.moviecatalogue.core.data.source.remote.response.Movies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundRepository<List<Movie>, List<Movies>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
//                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<Movies>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<Movies>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertPlayer(movieList)
            }
        }.asFlow()

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> =
        object : NetworkBoundRepository<Movie, DetailMovies>(){
            override fun loadFromDB(): Flow<Movie> =
                localDataSource.getDetailMovies(movieId).map {
                    DataMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: Movie?): Boolean {
                return data != null && data.genres == ""
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovies>> {
                return remoteDataSource.getDetailMovies(movieId)
            }

            override suspend fun saveCallResult(data: DetailMovies) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                GlobalScope.launch {
                    flowOf(data)
                        .collect { result ->
                            val movie = MovieEntity(
                                result.id,
                                result.title,
                                result.overview,
                                result.date,
                                result.poster,
                                result.rating,
                                genres.toString(),
                                false
                            )
                            localDataSource.setFavoriteMovie(movie, false)
                        }
                }
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getListFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutor.diskIo().execute { localDataSource.setFavoriteMovie(movieEntity, newState) }
    }


}