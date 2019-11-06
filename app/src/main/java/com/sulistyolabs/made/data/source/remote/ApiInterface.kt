package com.sulistyolabs.made.data.source.remote

import com.sulistyolabs.made.data.model.MovieModel
import com.sulistyolabs.made.data.model.TvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface  {


    @GET("movie")
    fun getMovie(@Query("api_key") api_key: String,
                 @Query("language") language: String) : Call<MovieModel>

    @GET("tv")
    fun getTvShow(@Query("api_key") api_key: String,
                 @Query("language") language: String) : Call<TvModel>


}