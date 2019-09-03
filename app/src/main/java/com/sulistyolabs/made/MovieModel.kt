package com.sulistyolabs.made

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val name: String?,
    val dec: String?,
    val quote: String?,
    val img: String?
) : Parcelable