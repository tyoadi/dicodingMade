package com.sulistyolabs.made

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowModel(
    val name: String?,
    val release: String,
    val dec: String?,
    val img: Int?
) : Parcelable