package com.example.pagmovies.network

import com.example.pagmovies.data.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") id: Int
    ) : MoviesResponse
}