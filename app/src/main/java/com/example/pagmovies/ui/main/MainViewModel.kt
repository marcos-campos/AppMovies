package com.example.pagmovies.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagmovies.data.MoviesResponse
import com.example.pagmovies.data.Result
import com.example.pagmovies.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel : ViewModel() {

    val repository = RepositoryApi()
    val errorMessage = MutableLiveData<String>()
    val listUpcomingMovies = MutableLiveData<MoviesResponse>()

    var nextPage = 1

    fun getUpcomingMoviesCoroutines() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                repository.getUpcomingMovies(nextPage).let {
                    newPage(it)
                    listUpcomingMovies.postValue(it)
                }
            }

            catch (error: Throwable){
                sendError(error)
            }
        }
    }

    fun newPage(moviesPage: MoviesResponse) {
        nextPage = moviesPage.page?.plus(1) ?: 1
    }

    fun getNextPageUpcomingMoviesCoroutines() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                repository.getUpcomingMovies(nextPage).let {
                    newPage(it)
                    listUpcomingMovies.postValue(it)
                }
            }

            catch (error: Throwable){
                sendError(error)
            }
        }
    }

    private fun sendError(error: Throwable) {
        when(error){
            is UnknownHostException -> errorMessage.postValue("verifique sua conexão")
        }
    }
}