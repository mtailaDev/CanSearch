package com.example.cansearch.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setNavClickListener()
        setDestinationListener()
    }

    private fun setDestinationListener() {
        val nav = Navigation.findNavController(this, R.id.nav_host_fragment)
        nav.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.trialFragment -> {
                    home_bottom_nav.gone()
                }
                R.id.searchFragment,
                R.id.archiveFragment,
                R.id.settingsFragment -> {
                    home_bottom_nav.visible()
                }
            }
        }
    }

    private fun setNavClickListener() {
        home_bottom_nav.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
    }
}