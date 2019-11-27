package com.sulistyolabs.made.ui.favorite.favmovie

import android.content.Context
import com.sulistyolabs.made.data.model.MovieItem

interface FavMovieInterface {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showDavoriteMovie(movieList: List<MovieItem>)
    }

    interface Presenter {
        fun getMovieData(context: Context)

    }
}