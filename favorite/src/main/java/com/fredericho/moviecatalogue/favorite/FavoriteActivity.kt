package com.fredericho.moviecatalogue.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fredericho.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.fredericho.moviecatalogue.favorite.di.favoriteModule
import com.fredericho.moviecatalogue.presentation.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private val favoriteViewModel : FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        loadKoinModules(favoriteModule)

        val favoriteAdapter = FavoriteAdapter()

        favoriteViewModel.favorite.observe(this, { movies ->
            if (movies != null) {
                favoriteAdapter.setData(movies)
                favoriteAdapter.notifyDataSetChanged()
                binding.tvFavoriteEmpty.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
            }
        })

        with(binding){
            rvMovieFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvMovieFavorite.setHasFixedSize(true)
            rvMovieFavorite.adapter = favoriteAdapter
        }

    }
}