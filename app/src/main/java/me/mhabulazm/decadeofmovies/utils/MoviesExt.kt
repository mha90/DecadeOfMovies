package me.mhabulazm.decadeofmovies.utils

import me.mhabulazm.decadeofmovies.model.Movie

fun List<Movie>.sortByYearDescending(): List<Movie> = sortedByDescending { it.year }

fun List<Movie>.limitMoviesToFiveByYear(): List<Movie> {
    val movies = mutableListOf<Movie>()
    if (size <= 5) return this
    val map = HashMap<Int, Int>()

    var year: Int

    for (movie in this) {
        year = movie.year
        if (map.containsKey(year)) {
            val occurrences: Int? = map[year]
            if (occurrences != null && occurrences <= 5) {
                movies.add(movie)
                map[year] = occurrences + 1
            }
        } else {
            map[year] = 1
        }
    }
    return movies
}