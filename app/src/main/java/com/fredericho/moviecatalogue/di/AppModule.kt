package com.fredericho.moviecatalogue.di

import com.fredericho.moviecatalogue.core.domain.usecase.MovieInteractor
import com.fredericho.moviecatalogue.core.domain.usecase.MovieUseCase
import com.fredericho.moviecatalogue.presentation.detail.DetailViewModel
import com.fredericho.moviecatalogue.presentation.home.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    factory<MovieUseCase>{ MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}


