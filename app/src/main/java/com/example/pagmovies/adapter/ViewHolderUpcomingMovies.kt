package com.example.pagmovies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagmovies.R

class ViewHolderUpcomingMovies (view: View): RecyclerView.ViewHolder(view)  {

    val imageMovie by lazy { view?.findViewById<ImageView>(R.id.recycler_iv_movie) }
//    val nameMovie by lazy { view?.findViewById<TextView>(R.id.recycler_tv_name) }
}