package com.example.cansearch.search.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen

class TrialActivity : AppCompatActivity() {

    private lateinit var viewModel: TrialActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trial)
        viewModel = ViewModelProviders.of(this)[TrialActivityViewModel::class.java]

        // todo - clean this
        val selectedTrial = intent.getParcelableExtra<SearchScreen.SearchResult>("Test")

        viewModel.selectedTrial.value = selectedTrial

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, TrialFragment.newInstance())
        ft.commit()
    }

    fun showLocation() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, SiteLocationFragment.newInstance())
        ft.commit()
    }
}