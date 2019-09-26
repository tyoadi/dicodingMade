package com.sulistyolabs.made.view.tvshow

import com.sulistyolabs.made.data.ApiClient
import com.sulistyolabs.made.model.TvShow
import com.sulistyolabs.made.model.TvShowItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowPresenter(private val mView: TvShowContract.View) : TvShowContract.Presenter {

    override fun getDataTvShow(apiKey: String, language: String) {
        mView.showLoading()

        val config = ApiClient.config()
        val call: Call<TvShow> = config.getTvShow(apiKey, language)

        call.enqueue(object : Callback<TvShow> {
            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                t.message?.let {mView.onError(it)}
                mView.hideLoading()
            }

            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {

                when(response.code()){

                    200 -> {
                        val data: List<TvShowItem>? = response.body()?.results
                        if (data?.equals(null)!!) {
                            mView.onEmpty()
                        } else {
                            mView.showListTvShow(data)
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