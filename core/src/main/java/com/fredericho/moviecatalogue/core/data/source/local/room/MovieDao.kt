package com.fredericho.moviecatalogue.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fredericho.moviecatalogue.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_entity")
    fun getAllMovies() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getDetailMovie(id : Int) : Flow<MovieEntity>


    @Query("SELECT * FROM movie_entity WHERE favorite = 1")
    fun getListFavoriteMovie() : Flow<List<MovieEntity>>

    @Insert
    suspend fun insertMovie(movieEntity: List<MovieEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)
}