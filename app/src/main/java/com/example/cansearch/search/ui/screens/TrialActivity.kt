package com.example.cansearch.search.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.search.di.SearchDagger
import javax.inject.Inject

class TrialActivity : AppCompatActivity() {

    private lateinit var viewModel: TrialActivityViewModel
    @Inject
    lateinit var viewModelFactory: TrialActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trial)
        SearchDagger.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[TrialActivityViewModel::class.java]

        // todo - clean this
        val selectedTrial = intent.getStringExtra("Test")
        viewModel.selectedTrialActivityID.value = selectedTrial

        viewModel.selectedTrial.observe(this, Observer { searchScreenResults ->
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment, TrialFragment.newInstance())
            ft.commit()
        })
        viewModel.getSearch()
    }

    fun showLocation() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, SiteLocationFragment.newInstance())
        ft.commit()
    }
}