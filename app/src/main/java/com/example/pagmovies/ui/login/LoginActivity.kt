package com.example.pagmovies.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pagmovies.R
import com.example.pagmovies.ui.profile.ProfileFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_login,
                    LoginFragment.newInstance()
                )
                .commitNow()
        }
    }
}