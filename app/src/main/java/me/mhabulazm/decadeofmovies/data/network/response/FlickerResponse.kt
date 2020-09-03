package me.mhabulazm.decadeofmovies.data.network.response

class FlickerResponse(
    val stat: String,
    val photos: PhotoResponse
)

class PhotoResponse(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>
)

class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int
)

