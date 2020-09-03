package me.mhabulazm.decadeofmovies.features.movieslist

import android.widget.Filter
import me.mhabulazm.decadeofmovies.data.MoviesLocalDataSource
import me.mhabulazm.decadeofmovies.model.Movie
import me.mhabulazm.decadeofmovies.utils.limitMoviesToFiveByYear
import me.mhabulazm.decadeofmovies.utils.sortByYearDescending

class MoviesSearchFilter(
    private val callback: ResultPublish
) : Filter() {

    interface ResultPublish {
        fun publishResults(movies: List<Movie>?)
    }

    override fun performFiltering(query: CharSequence?): FilterResults? {
        val searchResults = MoviesLocalDataSource.getTrie()?.startsWith(query.toString())
        val results = FilterResults()
        if (searchResults == null) return null

        results.count = searchResults.size
        results.values = searchResults
        return results
    }

    override fun publishResults(query: CharSequence?, filterResults: FilterResults?) {
        if (filterResults?.values != null) {
            val queryResults = filterResults.values as List<Movie>
            callback.publishResults(queryResults.sortByYearDescending().limitMoviesToFiveByYear())
        } else {
            callback.publishResults(null)

        }

    }

}