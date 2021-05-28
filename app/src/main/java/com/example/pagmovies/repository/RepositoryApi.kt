package com.example.pagmovies.repository

import androidx.fragment.app.FragmentPagerAdapter
import com.example.pagmovies.network.EndPoints
import com.example.pagmovies.network.RetrofitInit

class RepositoryApi  {

    companion object{
        const val chave = "875d4cd8158a587a94002943424d5f28"
    }

    private var url = "https://api.themoviedb.org/3/"

    private var service = EndPoints::class

    private val conectionService = RetrofitInit(url).create(service)

    suspend fun getUpcomingMovies(page: Int) = conectionService.getUpcomingMovies(page)

}