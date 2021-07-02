package com.fredericho.moviecatalogue.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.utils.ImageMovie
import com.fredericho.moviecatalogue.favorite.databinding.ItemFavoriteBinding
import com.fredericho.moviecatalogue.presentation.detail.DetailActivity
import okhttp3.internal.notify

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var listMovie = ArrayList<Movie>()

    fun setData(movie: List<Movie>){
        if (movie.isEmpty()) return
        listMovie.clear()
        listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemFavoriteBinding.bind(itemView)

        fun bind(movie : Movie){
            with(binding){
                tvTitle.text = movie.title
                tvRating.text = movie.rating.toString()
                movie.poster.let {
                    with(ImageMovie){
                        setImage(itemView.context, API_IMAGE + IMAGE_SIZE + it, tvPoster)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}