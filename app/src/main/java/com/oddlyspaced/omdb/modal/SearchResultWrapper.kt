package com.oddlyspaced.omdb.modal

import com.google.gson.annotations.SerializedName

data class SearchResultWrapper(
    @SerializedName("Search")
    val searchResults: List<SearchResult>?
)