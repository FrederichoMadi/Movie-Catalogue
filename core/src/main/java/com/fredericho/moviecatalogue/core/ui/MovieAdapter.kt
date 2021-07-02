package com.fredericho.moviecatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fredericho.moviecatalogue.core.R
import com.fredericho.moviecatalogue.core.databinding.ListItemBinding
import com.fredericho.moviecatalogue.core.domain.model.Movie
import com.fredericho.moviecatalogue.core.utils.ImageMovie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(movie : List<Movie>?){
        if (movie!!.isEmpty()) return
        listMovie.clear()
        listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ListItemBinding.bind(itemView)

        fun bind(movies : Movie){
            with(binding){
                tvTitle.text = movies.title
                movies.poster.let { poster ->
                    with(ImageMovie){
                        setImage(itemView.context, API_IMAGE + IMAGE_SIZE + poster, imgPoster)
                    }
                }

                itemView.setOnClickListener {
                    onItemClick?.invoke(listMovie[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

}