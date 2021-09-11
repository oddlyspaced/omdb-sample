package com.oddlyspaced.omdb.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.oddlyspaced.omdb.adapter.SearchResultAdapter
import com.oddlyspaced.omdb.api.ApiClient
import com.oddlyspaced.omdb.databinding.ActivityMainBinding
import com.oddlyspaced.omdb.modal.SearchResult
import com.oddlyspaced.omdb.modal.SearchResultWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// MainActivity -> Launched first when app is started
class MainActivity : AppCompatActivity() {

    // Api Client for our app
    private val client = ApiClient.getApiClient()
    // Array list which holds search results
    private val results = arrayListOf<SearchResult>()
    // Adapter for the search recycler view
    private val adapter = SearchResultAdapter(results)

    // Binding variable for MainActivity
    private lateinit var binding: ActivityMainBinding

    // Holds current page number
    private var currentPage = 0
    // Current search query
    private var currentQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialise binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply and setup Recycler View
        binding.rvSearchResult.apply {
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(applicationContext, 2) // Displays search result manager in 2 columns
        }

        // Set action for the Floating Action Button
        binding.fabSearch.setOnClickListener {
            // current search query
            val query = binding.etQuery.text.toString()
            if (currentQuery != query) {
                // If user entered new query reload results from start
                currentPage = 0
                // Count new items to notify adapter
                val count = results.size
                // clear results
                results.clear()
                // notify adapter that new items are added
                adapter.notifyItemRangeRemoved(0, count)
            }
            // Check for empty query
            if (query.isEmpty()) {
                Toast.makeText(applicationContext, "Please type a query!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Load results with the query
            currentQuery = query
            currentPage += 1
            loadResults(query, currentPage)
        }
    }

    /**
     * @param query Query to search for
     * @param page Page number
     */
    private fun loadResults(query: String, page: Int) {
        // search movies
        client.searchMovies(query, page).enqueue(object : Callback<SearchResultWrapper> {
            override fun onResponse(call: Call<SearchResultWrapper>, response: Response<SearchResultWrapper>) {
                response.body()?.let { body -> // body not null
                    if (body.searchResults == null) {
                        // search results null, so no results loaded
                        Toast.makeText(applicationContext, "No results for $query", Toast.LENGTH_LONG).show()
                    }
                    else {
                        // search results present, showcase them
                        binding.consSearchResult.isVisible = true
                        binding.consWaitingSearch.isVisible = false
                        results.addAll(body.searchResults)
                        adapter.notifyItemRangeInserted(results.size - body.searchResults.size, body.searchResults.size)
                    }
                }
            }

            // Api call failed
            override fun onFailure(call: Call<SearchResultWrapper>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Page End Reached!", Toast.LENGTH_LONG).show()
            }
        })
    }
}