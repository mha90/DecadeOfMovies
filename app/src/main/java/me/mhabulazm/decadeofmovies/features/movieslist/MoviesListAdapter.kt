package me.mhabulazm.decadeofmovies.features.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.model.Movie

class MoviesListAdapter(private val movies: List<Movie>, private val filter: Filter) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>(), Filterable {

    private var queryResults: List<Movie>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitleTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.movieTitleTextView) }
        private val ratingBar: AppCompatRatingBar by lazy {
            itemView.findViewById<AppCompatRatingBar>(
                R.id.movieRatingBar
            )
        }

        fun bind(movie: Movie) {
            movieTitleTextView.text = "${movie.title} : ${movie.year}"
            ratingBar.rating = movie.rating.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (hasQueryResults()) return queryResults!!.size
        return movies.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = if (hasQueryResults()) queryResults!![position] else movies[position]
        holder.bind(movie = movie)
    }

    private fun hasQueryResults() = queryResults != null

    override fun getFilter(): Filter {
        return filter
    }

    fun updateResults(results: List<Movie>?) {
        if (results == null) {
            queryResults = null

        }
        queryResults = results
        notifyDataSetChanged()
    }

}