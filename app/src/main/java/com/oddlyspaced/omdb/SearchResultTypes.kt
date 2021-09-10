package com.oddlyspaced.omdb

import com.google.gson.annotations.SerializedName

enum class SearchResultTypes(val type: String) {
    @SerializedName("movie")
    MOVIE("movie"),

    @SerializedName("series")
    SERIES("series")
}