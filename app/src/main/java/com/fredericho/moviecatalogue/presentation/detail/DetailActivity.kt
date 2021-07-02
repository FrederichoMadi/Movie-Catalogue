package com.fredericho.moviecatalogue.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.fredericho.moviecatalogue.R
import com.fredericho.moviecatalogue.core.data.source.Resource
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.utils.ImageMovie
import com.fredericho.moviecatalogue.databinding.ActivityDetailHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel : DetailViewModel by viewModel()
    private lateinit var binding : ActivityDetailHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val movieId = intent.getIntExtra(EXTRA_DATA, 0)

        viewModel.getDetailMovie(movieId).observe(this, { movies ->
            if (movies != null){
                when(movies){
                    is Resource.Loading -> detailLoading()
                    is Resource.Success ->{
                        if (movies.data != null){
                            showDetail()
                            populateMovie(movies.data!!)
                            shareClick(movies.data!!)
                        }
                    }
                    is Resource.Error -> {
                        showDetail()
                        Toast.makeText(this, "Oops... Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun populateMovie(movies: Movie) {
        with(binding){
            tvTitle.text = movies.title
            tvRating.text = movies.rating.toString()
            tvOverview.text = movies.overview
            tvDate.text = movies.date
            tvGenres.text = movies.genres

            var statusFavorite = movies.favorite
            setStatusFavorite(statusFavorite)
            binding.fabFav.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movies, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (statusFavorite){
                    Toast.makeText(this@DetailActivity, "Add favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DetailActivity, "Remove from favorite", Toast.LENGTH_SHORT).show()

                }

            }
            with(ImageMovie){
                setImageDetail(this@DetailActivity, API_IMAGE + IMAGE_SIZE + movies.poster, imgPoster)
            }
        }

    }

    private fun shareClick(movies : Movie){
        binding.imgShare.setOnClickListener {
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plain")
                    .setChooserTitle("Share this Film")
                    .setText(resources.getString(R.string.share_movie, movies.id.toString()))
                    .startChooser()
        }
    }

    private fun detailLoading(){
        with(binding){
            tvGenres.visibility = View.GONE
            tvDate.visibility = View.GONE
            tvOverview.visibility = View.GONE
            tvRating.visibility = View.GONE
            tvTitle.visibility = View.GONE
            imgBack.visibility = View.GONE
            imgShare.visibility = View.GONE
            imgPoster.visibility = View.GONE
            textView6.visibility = View.GONE
            fabFav.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showDetail(){
        with(binding){
            tvGenres.visibility = View.VISIBLE
            tvDate.visibility = View.VISIBLE
            tvOverview.visibility = View.VISIBLE
            tvRating.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            imgBack.visibility = View.VISIBLE
            imgShare.visibility = View.VISIBLE
            imgPoster.visibility = View.VISIBLE
            textView6.visibility = View.VISIBLE
            fabFav.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun setStatusFavorite(state : Boolean){
        if (state){
            binding.fabFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fabFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}