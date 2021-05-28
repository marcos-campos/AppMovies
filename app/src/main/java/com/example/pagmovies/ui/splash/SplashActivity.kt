package com.example.pagmovies.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pagmovies.MainActivity
import com.example.pagmovies.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({

            startActivity(Intent(this , MainActivity::class.java))

            finish ()
        },2700)
    }
}