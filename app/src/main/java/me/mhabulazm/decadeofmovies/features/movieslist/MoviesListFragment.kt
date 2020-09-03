package me.mhabulazm.decadeofmovies.features.movieslist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.features.main.MainActivityConnector
import me.mhabulazm.decadeofmovies.features.moviedetails.MovieDetailsFragment
import me.mhabulazm.decadeofmovies.model.Movie


class MoviesListFragment : Fragment(), MoviesSearchFilter.ResultPublish,
    MoviesListAdapter.OnMovieClickListener, IMoviesListView {

    private var adapter: MoviesListAdapter? = null
    private lateinit var filter: MoviesSearchFilter
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesSearchEditText: EditText
    private lateinit var presenter: IMoviesListPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_movies_list, container, false)
        moviesRecyclerView = fragmentView.findViewById(R.id.moviesRecyclerView)
        moviesSearchEditText = fragmentView.findViewById(R.id.moviesSearchEditText)
        presenter = MoviesListPresenter(this)
        attachTextWatcher()
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getMoviesList(requireActivity())
    }

    private fun attachTextWatcher() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
            }

            override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchForMovie(query.toString())
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
        updateResults(movies)
    }

    private fun updateResults(movies: List<Movie>?) {
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

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onMovieClick(movie: Movie) {
        hideKeyboardFrom(requireContext(), moviesSearchEditText)
        if (requireActivity() is MainActivityConnector) {
            (requireActivity() as MainActivityConnector).addFragment(
                MovieDetailsFragment.newInstance(
                    movie
                ), true
            )
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showNoResultsMessage() {
        onEmptyState()
    }

    override fun searchForMovie(searchQuery: String?) {
        if (searchQuery.isNullOrEmpty()) {
            updateResults(null)
            return
        }
        adapter?.filter?.filter(searchQuery)
    }

    override fun displayMoviesList(moviesList: List<Movie>) {
        initFilter()
        adapter = MoviesListAdapter(moviesList, filter, this)
        moviesRecyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        moviesRecyclerView.adapter = adapter
    }

}
