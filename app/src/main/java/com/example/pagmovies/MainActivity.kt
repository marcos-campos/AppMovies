package com.example.pagmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pagmovies.ui.main.MainFragment
import com.example.pagmovies.ui.navigation.MainNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainNavigation())
                    .commitNow()
        }
    }
}