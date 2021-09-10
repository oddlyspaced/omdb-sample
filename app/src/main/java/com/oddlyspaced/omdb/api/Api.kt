package com.oddlyspaced.omdb.api

import com.oddlyspaced.omdb.Global
import com.oddlyspaced.omdb.modal.SearchResultWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(" ")
    fun searchMovies(
        @Query("s") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apikey") apiKey: String = Global.API_KEY,
    ): Call<SearchResultWrapper>

}