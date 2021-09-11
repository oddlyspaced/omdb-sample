package com.oddlyspaced.omdb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.oddlyspaced.omdb.R
import com.oddlyspaced.omdb.api.ApiClient
import com.oddlyspaced.omdb.databinding.ActivityMovieDetailBinding
import com.oddlyspaced.omdb.modal.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private val client = ApiClient.getApiClient()

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imdbID = intent?.extras?.getString("imdb_id") ?: ""

        if (imdbID.isNotEmpty()) {
            client.getDetailsOfMovie(imdbID).enqueue(object : Callback<MovieDetails> {
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    response.body()?.let { details ->
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