package me.mhabulazm.decadeofmovies.data

import me.mhabulazm.decadeofmovies.data.network.FlickerApi
import me.mhabulazm.decadeofmovies.data.network.response.FlickerResponse
import retrofit2.Call

class MovieFlickerRemoteDataSource {

    fun getMovieImages(movieTitle: String): Call<FlickerResponse> {
        val map = mutableMapOf<String, String>()
        map["method"] = "flickr.photos.search"
        map["format"] = "json"
        map["nojsoncallback"] = "1"
        map["text"] = movieTitle
        map["page"] = "1"
        map["per_page"] = "20"

        return FlickerApi.getService().getMovieImages(map)
    }

}