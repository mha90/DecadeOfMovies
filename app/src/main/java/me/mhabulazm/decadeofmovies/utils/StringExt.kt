package me.mhabulazm.decadeofmovies.utils


import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.mhabulazm.decadeofmovies.data.MoviesResponse
import me.mhabulazm.decadeofmovies.model.Movie

fun String.parseMoviesList(): List<Movie>? {
    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<MoviesResponse> =
        moshi.adapter<MoviesResponse>(MoviesResponse::class.java)
    return adapter.fromJson(this)?.movies
}