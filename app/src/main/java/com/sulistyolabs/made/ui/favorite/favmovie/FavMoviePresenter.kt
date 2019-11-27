package com.sulistyolabs.made.ui.favorite.favmovie

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.source.local.MovieDB
import com.sulistyolabs.made.data.source.local.database
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavMoviePresenter (private val mView: FavMovieInterface.View) : FavMovieInterface.Presenter {

    override fun getMovieData(context: Context) {

        mView.showLoading()
        context.database.use {
            select(MovieDB.TABLE_FAVORITE_MOVIE).exec {
                val data = this.parseList(MyRowParserMatch())
                mView.hideLoading()
                mView.showDavoriteMovie(data)
            }
        }

    }

    class MyRowParserMatch : MapRowParser<MovieItem> {
        override fun parseRow(columns: Map<String, Any?>): MovieItem {
            val json: String? = columns[
                    MovieDB.JSON_DATA].toString()
            return Gson().fromJson<MovieItem>(json, MovieItem::class.java)
        }
    }

}