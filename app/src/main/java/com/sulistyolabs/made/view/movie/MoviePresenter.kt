package com.sulistyolabs.made.view.movie

import com.sulistyolabs.made.data.ApiClient
import com.sulistyolabs.made.model.Movie
import com.sulistyolabs.made.model.MovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val mView: MovieContract.View) : MovieContract.Presenter {

    override fun getDataMovie(apiKey: String, language: String) {
        mView.showLoading()

        val config = ApiClient.config()
        val call: Call<Movie> = config.getMovie(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.message?.let {mView.onError(it)}
                mView.hideLoading()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                when(response.code()){

                    200 -> {
                        val data: List<MovieItem>? = response.body()?.results
                        if (data?.equals(null)!!) {
                            mView.onEmpty()
                        } else {
                            mView.showListMovie(data)
                        }
                    }
                    else -> {
                        mView.onFailed(response.message())
                    }
                }
                mView.hideLoading()
            }
        })
    }

}