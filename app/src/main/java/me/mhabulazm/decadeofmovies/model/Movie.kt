package me.mhabulazm.decadeofmovies.model


// "title": "C Me Dance",
//            "year": 2009,
//            "cast": [
//                "Greg Robbins",
//                "Christina DeMarco",
//                "Laura Romeo",
//                "Peter Kent"
//            ],
//            "genres": [
//                "Thriller"
//            ],
//            "rating": 4

data class Movie(
    val title: String,
    val year: Int,
    val rating: Int,
    val cast: List<String>?,
    val genres: List<String>?
)