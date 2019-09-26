package com.sulistyolabs.made.data

import com.sulistyolabs.made.model.Movie
import com.sulistyolabs.made.model.MovieItem
import com.sulistyolabs.made.model.TvShow
import com.sulistyolabs.made.model.TvShowItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie")
    fun getMovie(@Query("api_key") api_key: String,
                   @Query("language") language: String

    ): Call<Movie>

    @GET("tv")
    fun getTvShow(@Query("api_key") api_key: String,
                   @Query("language") language: String

    ): Call<TvShow>

}