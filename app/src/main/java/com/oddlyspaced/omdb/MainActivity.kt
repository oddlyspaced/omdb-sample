package com.oddlyspaced.omdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.getApiClient()

        client.searchMovies("Avengers").enqueue(object : Callback<SearchResultWrapper> {
            override fun onResponse(call: Call<SearchResultWrapper>, response: Response<SearchResultWrapper>) {
                response.body()?.let {
                    it.searchResults.forEach { result ->
                        Log.e("AA", result.toString())
                    }
                }
            }

            override fun onFailure(call: Call<SearchResultWrapper>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}