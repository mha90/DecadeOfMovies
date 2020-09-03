package me.mhabulazm.decadeofmovies.features.moviedetails

interface IMovieDetailsView {

    fun showEmptyState()

    fun displayImages(imageUrls: List<String>)
    fun showProgress()
    fun hideProgress()

}