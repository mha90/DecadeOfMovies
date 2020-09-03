package me.mhabulazm.decadeofmovies.features.moviedetails

import me.mhabulazm.decadeofmovies.data.MovieFlickerRemoteDataSource
import me.mhabulazm.decadeofmovies.data.network.response.FlickerResponse
import me.mhabulazm.decadeofmovies.data.network.response.Photo
import me.mhabulazm.decadeofmovies.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface IMovieDetailsPresenter {
    fun getMovieImages()
}

class MovieDetailsPresenter(private val movie: Movie, private val view: IMovieDetailsView) :
    IMovieDetailsPresenter {

    override fun getMovieImages() {

        view.showProgress()
        val callback = object : Callback<FlickerResponse> {
            override fun onFailure(call: Call<FlickerResponse>, t: Throwable) {
                view.hideProgress()
                view.showEmptyState()
            }

            override fun onResponse(
                call: Call<FlickerResponse>,
                response: Response<FlickerResponse>
            ) {
                view.hideProgress()
                onResponse(response)
            }
        }

        MovieFlickerRemoteDataSource().getMovieImages(movieTitle = movie.title).enqueue(callback)
    }

    private fun onResponse(response: Response<FlickerResponse>) {
        if (response.isSuccessful && response.body() != null && response.body()!!.stat == "ok") {
            val photos = response.body()!!.photos.photo
            view.displayImages(photos.getImagesUrls())
        } else {
            view.showEmptyState()
        }
    }

    private fun List<Photo>.getImagesUrls(): List<String> {
        val urls = mutableListOf<String>()
        for (flickerEntry in this) {
            urls.add("http://farm${flickerEntry.farm}.static.flickr.com/${flickerEntry.server}/${flickerEntry.id}_${flickerEntry.secret}.jpg")
        }
        return urls
    }

}