package com.example.cansearch.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cansearch.R
import com.example.cansearch.search.SearchFragment
import com.example.cansearch.trial.data.DetailedTrial
import com.example.cansearch.trial.ui.TrialActivity

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, SearchFragment())
        ft.commit()
    }

    fun showtrial(trial: DetailedTrial) {
        startActivity(Intent(this, TrialActivity::class.java))
    }
}