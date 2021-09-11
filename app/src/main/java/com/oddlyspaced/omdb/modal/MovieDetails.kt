package com.oddlyspaced.omdb.modal

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Rated")
    val rated: String,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("Poster")
    val poster: String
)

/*
{
    "Title": "The Avengers",
    "Year": "2012",
    "Rated": "PG-13",
    "Released": "04 May 2012",
    "Runtime": "143 min",
    "Genre": "Action, Adventure, Sci-Fi",
    "Director": "Joss Whedon",
    "Writer": "Joss Whedon, Zak Penn",
    "Actors": "Robert Downey Jr., Chris Evans, Scarlett Johansson",
    "Plot": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
    "Language": "English, Russian, Hindi",
    "Country": "United States",
    "Awards": "Nominated for 1 Oscar. 38 wins & 80 nominations total",
    "Poster": "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
    "Ratings": [
        {
            "Source": "Internet Movie Database",
            "Value": "8.0/10"
        },
        {
            "Source": "Metacritic",
            "Value": "69/100"
        }
    ],
    "Metascore": "69",
    "imdbRating": "8.0",
    "imdbVotes": "1,301,863",
    "imdbID": "tt0848228",
    "Type": "movie",
    "DVD": "25 Sep 2012",
    "BoxOffice": "$623,357,910",
    "Production": "Marvel Studios",
    "Website": "N/A",
    "Response": "True"
}
 */