package com.example.pagmovies.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagmovies.R
import com.example.pagmovies.adapter.AdapterUpcomingMovies
import com.example.pagmovies.data.Result
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {

    private var listUpcomingMovies = mutableListOf<Result>()
    val recyclerUpcomingMovies by lazy {view?.findViewById<RecyclerView>(R.id.recyler_upcoming_movies)}
//    val title by lazy {view?.findViewById<TextView>(R.id.title)}
//    val img by lazy {view?.findViewById<ImageView>(R.id.img)}

    private val recyclerScrollListener by lazy {
        RecyclerScrollListener {
            viewModel.getNextPageUpcomingMoviesCoroutines()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getUpcomingMoviesCoroutines()

        val linearLayout = LinearLayoutManager(activity)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        recyclerUpcomingMovies?.layoutManager = linearLayout

        val adapter = activity?.let { AdapterUpcomingMovies() }
        recyclerUpcomingMovies?.adapter = adapter

        recyclerUpcomingMovies?.addOnScrollListener(recyclerScrollListener)

//        val adapter = activity?.let { AdapterUpcomingMovies(listUpcomingMovies, it) }
//        recyclerUpcomingMovies?.adapter = adapter

        viewModel.listUpcomingMovies.observe(viewLifecycleOwner, Observer {moviesResponse ->

            moviesResponse.results?.let {

                setRequestingNextPage()
                adapter?.addMovies(it as MutableList<Result>)

//                listUpcomingMovies.addAll(it)
//                adapter?.notifyDataSetChanged()
//                title?.text = listUpcomingMovies[11].title
//                Picasso.with(activity).load(listUpcomingMovies[11].poster_path).into(img)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT)
        })
    }

    private fun setRequestingNextPage(){
        recyclerScrollListener.requesting = false
    }
}
