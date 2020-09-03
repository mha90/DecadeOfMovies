package me.mhabulazm.decadeofmovies.features.moviedetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_details.*
import me.mhabulazm.decadeofmovies.R
import me.mhabulazm.decadeofmovies.model.Movie

private const val ARG_MOVIE = ""

class MovieDetailsFragment : Fragment(), IMovieDetailsView {
    private var movie: Movie? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: IMovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        recyclerView = fragmentView.findViewById(R.id.movieImagesRecyclerView)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movie?.let {
            val title = it.title
            presenter = MovieDetailsPresenter(it, this)
            movieTitleTextView.text = title
            presenter.getMovieImages()
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE, movie)
                }
            }
    }

    override fun showEmptyState() {
        Toast.makeText(activity, "No data found", Toast.LENGTH_SHORT).show()
    }

    override fun displayImages(imageUrls: List<String>) {
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        recyclerView.adapter = MovieImagesListAdapter(imageUrls)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}

