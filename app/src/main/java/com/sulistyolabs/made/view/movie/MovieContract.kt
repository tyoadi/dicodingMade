package com.sulistyolabs.made.view.movie

import com.sulistyolabs.made.base.BaseContract
import com.sulistyolabs.made.model.Movie
import com.sulistyolabs.made.model.MovieItem

interface MovieContract {

    interface View : BaseContract {
        fun showListMovie(data: List<MovieItem>)
        fun onEmpty()
    }

    interface Presenter {
        fun getDataMovie(apiKey: String, language: String)
    }
}