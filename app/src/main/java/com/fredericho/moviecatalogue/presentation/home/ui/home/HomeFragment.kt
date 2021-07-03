package com.fredericho.moviecatalogue.presentation.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.fredericho.moviecatalogue.core.data.source.Resource
import com.fredericho.moviecatalogue.core.ui.MovieAdapter
import com.fredericho.moviecatalogue.databinding.FragmentHomeBinding
import com.fredericho.moviecatalogue.presentation.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : MovieAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            adapter = MovieAdapter()

            adapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectData.id)
                startActivity(intent)
            }

            homeViewModel.allMovie.observe(viewLifecycleOwner, { movies ->
                if (movies != null){
                    when(movies){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(view.context, "Opps... Something wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

            with(binding){
                rvMovies.layoutManager = GridLayoutManager(view.context, 2)
                rvMovies.setHasFixedSize(true)
                rvMovies.adapter = adapter
            }
        }
    }
}