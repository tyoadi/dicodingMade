package com.sulistyolabs.made.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val page: Int,
    val result: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable