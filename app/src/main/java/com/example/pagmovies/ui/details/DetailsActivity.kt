package com.example.pagmovies.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pagmovies.R
import com.example.pagmovies.data.Result
import com.example.pagmovies.ui.profile.ProfileFragment

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val info = intent.extras
        val movies = info?.getSerializable("movies") as Result

        if (savedInstanceState == null) {
            movies?.let {
                DetailsFragment.newInstance(it)
            }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_detail, it)
                    .commitNow()
            }
        }
    }
}