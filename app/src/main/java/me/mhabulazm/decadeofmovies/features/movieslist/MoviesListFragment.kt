package me.mhabulazm.decadeofmovies.features.movieslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.data.MoviesLocalDataSource
import me.mhabulazm.decadeofmovies.model.Movie
import me.mhabulazm.decadeofmovies.utils.parseMoviesList
import me.mhabulazm.decadeofmovies.utils.readRawFile

class MoviesListFragment : Fragment(), MoviesSearchFilter.ResultPublish {

    private var adapter: MoviesListAdapter? = null
    private lateinit var filter: MoviesSearchFilter
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesSearchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_movies_list, container, false)
        moviesRecyclerView = fragmentView.findViewById(R.id.moviesRecyclerView)
        moviesSearchEditText = fragmentView.findViewById(R.id.moviesSearchEditText)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initFilter()
        initAdapter()
        attachTextWatcher()
    }

    private fun initAdapter() {
        val moviesContentString = activity?.readRawFile(R.raw.movies)
        val moviesList: List<Movie>? = moviesContentString?.parseMoviesList()

        if (moviesList != null) {
            MoviesLocalDataSource.populateTrie(moviesList)

            adapter = MoviesListAdapter(moviesList, filter)
            moviesRecyclerView.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            moviesRecyclerView.adapter = adapter
            moviesRecyclerView.setHasFixedSize(true)
        }
    }

    private fun attachTextWatcher() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().isBlank()) {
                    adapter?.updateResults(null)
                }
            }

            override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter?.filter?.filter(query)
            }
        }
        moviesSearchEditText.addTextChangedListener(textWatcher)
    }

    private fun initFilter() {
        filter = MoviesSearchFilter(this)
    }

    override fun publishResults(movies: List<Movie>?) {
        if (movies == null) {
            onEmptyState()
        }
        adapter?.updateResults(movies)
    }

    private fun onEmptyState() {
        Toast.makeText(activity, "No results found", Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(): MoviesListFragment {
            return MoviesListFragment()
        }
    }

}
