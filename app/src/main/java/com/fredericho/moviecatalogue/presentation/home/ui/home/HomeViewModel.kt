package com.fredericho.moviecatalogue.presentation.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fredericho.moviecatalogue.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val allMovie = movieUseCase.getAllMovie().asLiveData()
}