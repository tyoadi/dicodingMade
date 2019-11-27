package com.sulistyolabs.made.ui.favorite.favtv

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.made.data.model.MovieItem
import com.sulistyolabs.made.data.model.TvItem
import com.sulistyolabs.made.data.source.local.MovieDB
import com.sulistyolabs.made.data.source.local.TvshowDB
import com.sulistyolabs.made.data.source.local.database
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavTvPresenter (private val mView: FavTvInterface.View) : FavTvInterface.Presenter {

    override fun getTvData(context: Context) {
        mView.showLoading()
        context.database.use {
            select(TvshowDB.TABLE_FAVORITE_TV).exec {
                val data = this.parseList(MyRowParserMatch())
                mView.hideLoading()
                mView.showDavoriteTv(data)
            }
        }
    }

    class MyRowParserMatch : MapRowParser<TvItem> {
        override fun parseRow(columns: Map<String, Any?>): TvItem {
            val json: String? = columns[
                    MovieDB.JSON_DATA].toString()
            return Gson().fromJson<TvItem>(json, TvItem::class.java)
        }
    }

}