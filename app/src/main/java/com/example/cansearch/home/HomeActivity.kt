package com.example.cansearch.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.screens.SearchFragment
import com.example.cansearch.search.ui.screens.TrialActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, SearchFragment())
        ft.commit()
    }

    fun showTrial(selectedTrial: SearchScreen.SearchResult) {
        val intent = Intent(this, TrialActivity::class.java)
        intent.putExtra("Test", selectedTrial)
        startActivity(intent)
    }
}