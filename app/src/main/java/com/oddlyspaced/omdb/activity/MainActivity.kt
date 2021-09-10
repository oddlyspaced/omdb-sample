package com.oddlyspaced.omdb.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.oddlyspaced.omdb.R
import com.oddlyspaced.omdb.SearchResultAdapter
import com.oddlyspaced.omdb.api.ApiClient
import com.oddlyspaced.omdb.databinding.ActivityMainBinding
import com.oddlyspaced.omdb.modal.SearchResult
import com.oddlyspaced.omdb.modal.SearchResultWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val client = ApiClient.getApiClient()
    private val results = arrayListOf<SearchResult>()
    private val adapter by lazy { SearchResultAdapter(results) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSearchResult.apply {
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }

        client.searchMovies("Avengers").enqueue(object : Callback<SearchResultWrapper> {
            override fun onResponse(call: Call<SearchResultWrapper>, response: Response<SearchResultWrapper>) {
                response.body()?.let {
                    binding.rvSearchResult.isVisible = true
                    binding.consWaitingSearch.isVisible = false
                    results.addAll(it.searchResults)
//                    adapter.notifyDataSetChanged()
                    adapter.notifyItemRangeInserted(results.size - it.searchResults.size, it.searchResults.size)
                }
            }

            override fun onFailure(call: Call<SearchResultWrapper>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}