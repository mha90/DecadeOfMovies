package me.mhabulazm.decadeofmovies.features.movieslist

import me.mhabulazm.decadeofmovies.model.Movie

interface IMoviesListView {
    fun showNoResultsMessage()
    fun searchForMovie(searchQuery: String?)
    fun displayMoviesList(moviesList: List<Movie>)
}
