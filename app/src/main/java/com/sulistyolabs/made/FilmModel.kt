package com.sulistyolabs.made

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmModel(
    val name: String?,
    val dec: String?,
    val quote: String?,
    val img: String?
) : Parcelable