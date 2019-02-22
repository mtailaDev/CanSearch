package com.example.cansearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cansearch.home.HomeActivity
import com.example.cansearch.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, LoginFragment())
        ft.commit()

        login_tv_skip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}