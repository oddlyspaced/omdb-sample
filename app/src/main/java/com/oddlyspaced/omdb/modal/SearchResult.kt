package com.oddlyspaced.omdb.modal

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbId: String,
    @SerializedName("Type")
    val type: SearchResultTypes,
    @SerializedName("Poster")
    val posterLink: String,
)