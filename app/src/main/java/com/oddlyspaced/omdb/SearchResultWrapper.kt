package com.oddlyspaced.omdb

import com.google.gson.annotations.SerializedName

data class SearchResultWrapper(
    @SerializedName("Search")
    val searchResults: List<SearchResult>
)