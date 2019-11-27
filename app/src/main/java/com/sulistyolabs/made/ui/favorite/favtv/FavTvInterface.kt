package com.sulistyolabs.made.ui.favorite.favtv

import android.content.Context
import com.sulistyolabs.made.data.model.TvItem

interface FavTvInterface {


    interface View {
        fun hideLoading()
        fun showLoading()
        fun showDavoriteTv(movieList: List<TvItem>)
    }

    interface Presenter {
        fun getTvData(context: Context)

    }
}