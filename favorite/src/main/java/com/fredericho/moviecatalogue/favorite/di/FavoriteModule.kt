package com.fredericho.moviecatalogue.favorite.di

import com.fredericho.moviecatalogue.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}