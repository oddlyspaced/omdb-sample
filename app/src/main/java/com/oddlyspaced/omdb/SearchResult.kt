package com.oddlyspaced.omdb

data class SearchResult(
    val title: String,
    val year: Int,
    val imdbId: String,
    val type: SearchResultTypes,
    val posterLink: String,
)