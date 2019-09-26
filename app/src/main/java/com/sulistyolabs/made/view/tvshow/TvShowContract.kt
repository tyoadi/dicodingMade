package com.sulistyolabs.made.view.tvshow

import com.sulistyolabs.made.base.BaseContract
import com.sulistyolabs.made.model.TvShowItem

interface TvShowContract {

    interface View : BaseContract {
        fun showListTvShow(data: List<TvShowItem>)
        fun onEmpty()

    }

    interface Presenter {
        fun getDataTvShow(apiKey: String, language: String)
    }
}