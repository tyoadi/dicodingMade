package com.sulistyolabs.made.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.MovieModel
import com.sulistyolabs.made.data.model.TvItem
import com.sulistyolabs.made.data.model.TvModel
import com.sulistyolabs.made.data.source.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listTv = MutableLiveData<List<TvItem>>()
    val listMovie = MutableLiveData<List<MovieItem>>()

    internal fun setTvShow(apiKey: String, lang: String) {

        val config = ApiConfig.config()
        val call = config.getTvShow(apiKey, lang)

        call.enqueue(object : Callback<TvModel> {
            override fun onFailure(call: Call<TvModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<TvModel>, response: Response<TvModel>) {
                if (response.isSuccessful) {
                    val data: List<TvItem>? = response.body()?.results
                    listTv.postValue(data)
                }
            }
        })

    }

    internal fun getTvShow() : LiveData<List<TvItem>>{
        return listTv
    }

    internal fun setMovie(apiKey: String, lang: String) {

        val config = ApiConfig.config()
        val call = config.getMovie(apiKey, lang)

        call.enqueue(object : Callback<MovieModel> {
            override fun onFailure(call: Call<MovieModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful) {
                    val data: List<MovieItem>? = response.body()?.results
                    listMovie.postValue(data)
                }
            }
        })

    }

    internal fun getMovie() : LiveData<List<MovieItem>> {
        return listMovie
    }
}


