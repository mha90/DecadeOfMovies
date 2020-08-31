package me.mhabulazm.decadeofmovies.utils

import me.mhabulazm.decadeofmovies.model.Movie
import org.junit.Test

import org.junit.Assert.*

class MoviesExtKtTest {

    @Test
    fun sortByYear() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie("first", 1992, 1, null, null))
        movies.add(Movie("second", 2000, 2, null, null))
        movies.add(Movie("kill la kill", 2001, 3, null, null))
        movies.add(Movie("kill bill", 2001, 3, null, null))
        movies.add(Movie("fourth", 2004, 4, null, null))
        val sortedMovies = movies.sortByYearDescending()
        assertTrue(sortedMovies[0].title == "fourth")
        assertTrue(sortedMovies.size == movies.size)
    }


    @Test
    fun limitByFivePerYear() {
        val sixMoviesList = mutableListOf<Movie>()
        sixMoviesList.add(Movie("killing", 2000, 1, null, null))
        sixMoviesList.add(Movie("killing them softly", 2000, 1, null, null))
        sixMoviesList.add(Movie("kill", 2000, 2, null, null))
        sixMoviesList.add(Movie("kill la kill", 2000, 3, null, null))
        sixMoviesList.add(Movie("kill bill", 2000, 3, null, null))
        sixMoviesList.add(Movie("kill bill 2", 2000, 4, null, null))

        val limitedMovies = sixMoviesList.limitMoviesToFiveByYear()

        assertTrue(limitedMovies.size <= 5)
    }
}