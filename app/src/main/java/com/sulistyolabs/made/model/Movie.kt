package com.sulistyolabs.made.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieItem>?
) : Parcelable