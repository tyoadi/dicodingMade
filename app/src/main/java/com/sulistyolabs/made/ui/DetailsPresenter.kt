package com.sulistyolabs.made.ui

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.TvItem
import com.sulistyolabs.made.data.source.local.MovieDB
import com.sulistyolabs.made.data.source.local.TvshowDB
import com.sulistyolabs.made.data.source.local.database
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DetailsPresenter () : DetailsInterface.Presenter {

    override fun addFavoriteMovie(dataMovie: MovieItem, context: Context) {
        val gson = Gson()
        val json = gson.toJson(dataMovie)
        context.database.use {
            insert(
                MovieDB.TABLE_FAVORITE_MOVIE,
                MovieDB.MOVIE_ID to dataMovie?.id,
                MovieDB.JSON_DATA to json
            )
        }
    }

    override fun addFavoriteTvShow(dataTvShow: TvItem, context: Context) {
        val gson = Gson()
        val json = gson.toJson(dataTvShow)
        context.database.use {
            insert(
                TvshowDB.TABLE_FAVORITE_TV,
                TvshowDB.TVSHOW_ID to dataTvShow?.id,
                TvshowDB.JSON_DATA to json
            )

        }

    }

    override fun removeFavoriteMovie(id: String, context: Context) {
        context.database.use {
            delete(MovieDB.TABLE_FAVORITE_MOVIE, "(MOVIE_ID = {id})", "id" to id)
        }
    }

    override fun removeFavoriteTvShow(id: String, context: Context) {
        context.database.use {
            delete(TvshowDB.TABLE_FAVORITE_TV, "(TVSHOW_ID = {id})", "id" to id)
        }
    }


}