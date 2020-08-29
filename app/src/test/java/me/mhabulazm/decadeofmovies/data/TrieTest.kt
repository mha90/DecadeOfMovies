package me.mhabulazm.decadeofmovies.data

import me.mhabulazm.decadeofmovies.model.Movie
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TrieTest {
    private val trie = Trie()

    @Before
    fun setUp() {
        trie.insert(Movie("12 rounds", 2009, 1, null, null))
        trie.insert(Movie("The Avengers", 2012, 5, null, null))
        trie.insert(Movie("Captain America", 2002, 1, null, null))
        trie.insert(Movie("Captain Phillips", 2013, 5, null, null))
        trie.insert(Movie("Kill Bill", 2001, 4, null, null))
        trie.insert(Movie("Kill Bill II", 2003, 4, null, null))
        trie.insert(Movie("Kill", 2008, 1, null, null))
        trie.insert(Movie("Kill la kill", 2013, 2, null, null))
        trie.insert(Movie("Kill a mocking bird", 1962, 5, null, null))
    }

    @Test
    fun searchForAMovieShouldReturnIt() {
        val searchResult = trie.searchForMovie("12 rounds")
        assertTrue("Should have 12 ", searchResult != null)
        assertTrue("Rating should be 1", searchResult!!.rating == 1)
    }

    @Test
    fun searchForExistingMoviesReturnList() {
        val queryResult = trie.startsWith("kill")
        assertTrue("Should have Kill ", queryResult != null && queryResult.isNotEmpty())
        assertTrue("Should have 2 results for Kill", queryResult!!.size == 5)
    }

    @Test
    fun searchForNonExistingMovieReturnsEmptyResult() {
        assertTrue(
            "Searching for a movie that doesn't exist should return empty result",
            trie.searchForMovie("Handover") == null
        )
    }

    @Test
    fun searchForNonExistingMoviesReturnsEmptyResult() {
        assertTrue(
            "Searching for movies that doesn't exist should return empty result",
            trie.startsWith("Handover") == null
        )
    }
}