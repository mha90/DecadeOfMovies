package me.mhabulazm.decadeofmovies.features.movieslist

import android.content.Context
import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.data.MoviesLocalDataSource
import me.mhabulazm.decadeofmovies.model.Movie
import me.mhabulazm.decadeofmovies.utils.parseMoviesList
import me.mhabulazm.decadeofmovies.utils.readRawFile

interface IMoviesListPresenter {

    fun getMoviesList(context: Context)
}

class MoviesListPresenter(private val view: IMoviesListView) : IMoviesListPresenter {

    override fun getMoviesList(context: Context) {
        val moviesContentString = context.readRawFile(R.raw.movies)
        val moviesList: List<Movie>? = moviesContentString?.parseMoviesList()

        if (moviesList != null) {
            MoviesLocalDataSource.populateTrie(moviesList)
            view.displayMoviesList(moviesList)
        } else {
            view.showNoResultsMessage()
        }
    }

}