package com.example.cansearch.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.cansearch.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setNavClickListener()
    }

    private fun setNavClickListener() {
        home_bottom_nav.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
    }
}