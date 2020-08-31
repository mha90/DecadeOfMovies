package me.mhabulazm.decadeofmovies.data

import me.mhabulazm.decadeofmovies.model.Movie

object MoviesLocalDataSource {
    private var trie: Trie? = Trie()

    fun getTrie(): Trie? {
        return trie
    }

    fun populateTrie(moviesList: List<Movie>) {
        if (trie == null) return
        for (movie in moviesList) {
            trie?.insert(movie)
        }
    }

    fun clear() {
        trie = null
    }

}