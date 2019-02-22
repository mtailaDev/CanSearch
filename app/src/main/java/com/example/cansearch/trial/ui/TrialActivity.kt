package com.example.cansearch.trial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cansearch.R

class TrialActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trial)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, TrialFragment())
        ft.commit()
    }
}