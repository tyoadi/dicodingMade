package com.sulistyolabs.made.ui

import android.content.Context
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.TvItem

interface DetailsInterface {

    interface View {}

    interface Presenter {
        fun addFavoriteMovie(dataMovie: MovieItem, context: Context)
        fun addFavoriteTvShow(dataTvShow: TvItem, context: Context)
        fun removeFavoriteMovie(id: String, context: Context)
        fun removeFavoriteTvShow(id: String, context: Context)
    }
}