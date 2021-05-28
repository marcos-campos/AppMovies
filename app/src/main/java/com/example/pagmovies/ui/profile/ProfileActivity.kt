package com.example.pagmovies.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pagmovies.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_profile,
                    ProfileFragment.newInstance()
                )
                .commitNow()
        }
    }
}