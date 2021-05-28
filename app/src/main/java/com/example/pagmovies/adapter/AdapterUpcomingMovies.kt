package com.example.pagmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagmovies.R
import com.example.pagmovies.data.Result
import com.squareup.picasso.Picasso

class AdapterUpcomingMovies: RecyclerView.Adapter<ViewHolderUpcomingMovies>() {

    val upcomingMoviesList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUpcomingMovies {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_recycler, parent, false)
        return ViewHolderUpcomingMovies(view)
    }

    override fun getItemCount(): Int = upcomingMoviesList.size

    override fun onBindViewHolder(holder: ViewHolderUpcomingMovies, position: Int) {


        val baseUrl = "https://image.tmdb.org/t/p/"
        val tamanhoImage = "w500/"

        val imgMovie = holder.imageMovie
        Picasso.get().load(baseUrl + tamanhoImage + upcomingMoviesList[position].poster_path).fit().into(imgMovie)

//        val nameMovie = holder.nameMovie
//        nameMovie.text = upcomingMoviesList[position].title
    }

    fun addMovies(movies: MutableList<Result>){
        upcomingMoviesList.addAll(movies)
        notifyDataSetChanged()
    }
}