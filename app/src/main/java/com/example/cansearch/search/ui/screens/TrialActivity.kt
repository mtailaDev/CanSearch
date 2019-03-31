package com.example.cansearch.search.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.search.di.SearchDagger
import javax.inject.Inject

class TrialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trial)
    }

    fun showLocation() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, SiteLocationFragment.newInstance())
        ft.commit()
    }
}