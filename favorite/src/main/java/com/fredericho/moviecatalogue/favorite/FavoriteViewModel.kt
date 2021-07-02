package com.fredericho.moviecatalogue.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fredericho.moviecatalogue.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favorite = movieUseCase.getFavoriteMovie().asLiveData()
}