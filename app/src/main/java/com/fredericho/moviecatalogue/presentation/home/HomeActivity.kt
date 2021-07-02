package com.fredericho.moviecatalogue.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fredericho.moviecatalogue.R
import com.fredericho.moviecatalogue.databinding.ActivityHomeBinding
import com.fredericho.moviecatalogue.presentation.home.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val homeFragment = HomeFragment()

        setCurrentView(homeFragment)
        changeIcon(binding.imgNavHome, R.drawable.ic_home_active)

        with(binding){
            navHome.setOnClickListener {
                setCurrentView(homeFragment)

                changeIcon(imgNavHome, R.drawable.ic_home_active)
                changeIcon(imgNavFavorite, R.drawable.ic_baseline_favorite_24)
            }

            navFavorite.setOnClickListener {
                val uri = Uri.parse("moviecatalogue://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }

    private fun setCurrentView(fragment : Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
    }

    private fun changeIcon(image : ImageView, resource : Int){
        image.setImageResource(resource)
    }


}