package me.mhabulazm.decadeofmovies.features.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.model.Movie

class MoviesListAdapter(
    private val movies: List<Movie>,
    private val filter: Filter,
    private val onMovieClickListener: OnMovieClickListener
) :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(DIFF_CALLBACK), Filterable {

    private var queryResults: List<Movie>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitleTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.movieTitleTextView) }
        private val ratingBar: AppCompatRatingBar by lazy {
            itemView.findViewById<AppCompatRatingBar>(
                R.id.movieRatingBar
            )
        }

        fun bind(movie: Movie, onMovieClickListener: OnMovieClickListener) {
            movieTitleTextView.text = "${movie.title}: ${movie.year}"
            ratingBar.rating = movie.rating.toFloat()
            movieTitleTextView.setOnClickListener {
                onMovieClickListener.onMovieClick(movie)
            }
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
        holder.bind(movie = movie, onMovieClickListener = onMovieClickListener)
    }

    private fun hasQueryResults(): Boolean {
        return queryResults != null && queryResults!!.isNotEmpty()
    }

    override fun getFilter(): Filter {
        return filter
    }

    fun updateResults(results: List<Movie>?) {
        if (results == null) {
            queryResults = null

        } else {
            queryResults = results
            notifyDataSetChanged()
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

}