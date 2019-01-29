package com.example.cansearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cansearch.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, LoginFragment())
        ft.commit()
    }
}