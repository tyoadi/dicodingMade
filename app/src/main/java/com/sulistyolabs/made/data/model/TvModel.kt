package com.sulistyolabs.made.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvModel(
    val page: Int,
    val results: List<TvItem>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable