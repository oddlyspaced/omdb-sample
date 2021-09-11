package com.oddlyspaced.omdb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.oddlyspaced.omdb.api.ApiClient
import com.oddlyspaced.omdb.databinding.ActivityMovieDetailBinding
import com.oddlyspaced.omdb.modal.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// MovieDetailActivity -> Loaded when the user taps on an item
class MovieDetailActivity : AppCompatActivity() {

    // Get api client
    private val client = ApiClient.getApiClient()
    // Binding variable for Movie Details Activity
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load binding
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get imdb id from extras
        val imdbID = intent?.extras?.getString("imdb_id") ?: ""

        // check if imdb id is not empty
        if (imdbID.isNotEmpty()) {
            // fetch movie details by id
            client.getDetailsOfMovie(imdbID).enqueue(object : Callback<MovieDetails> {
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    response.body()?.let { details ->
                        // details not null
                        // apply details to activity
                        binding.apply {
                            txMovieTitle.text = details.title
                            txMovieActors.text = details.actors
                            txMovieDirector.text = details.director
                            txMovieGenre.text = details.genre
                            txMoviePlot.text = details.plot
                            txMovieRated.text = details.rated
                            txMovieRating.text = details.imdbRating
                            txMovieRelease.text = details.released
                            Glide.with(applicationContext).load(details.poster).into(binding.imgMovie)
                        }
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

    }
}