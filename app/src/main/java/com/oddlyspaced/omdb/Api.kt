package com.oddlyspaced.omdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET
    fun searchMovies(
        @Query("apikey") apiKey: String = Global.API_KEY,
        @Query("s") searchQuery: String,
        @Query("page") pageNumber: Int,
    ): Call<SearchResultWrapper>

}