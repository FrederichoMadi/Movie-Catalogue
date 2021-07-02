package com.fredericho.moviecatalogue.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fredericho.moviecatalogue.core.data.source.Resource
import com.fredericho.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.domain.usecase.MovieUseCase

class DetailViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetailMovie(id : Int) = movieUseCase.getDetailMovie(id).asLiveData()

    fun setFavoriteMovie(movie : Movie, newState : Boolean) =
            movieUseCase.setFavoriteMovie(movie, newState)

}