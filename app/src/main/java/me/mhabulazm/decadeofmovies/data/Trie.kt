package me.mhabulazm.decadeofmovies.data

import me.mhabulazm.decadeofmovies.model.Movie

class Trie {

    data class Node(
        var movie: Movie? = null,
        val childNodes: MutableMap<Char, Node> = mutableMapOf()
    )

    private val root = Node()
    private var queryResult = mutableListOf<Movie>()

    fun insert(movie: Movie) {
        var currentNode = root
        for (char in movie.title.toLowerCase()) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.movie = movie
    }

    private fun search(title: String): Node? {
        var currentNode = root
        for (char in title) {
            if (currentNode.childNodes[char] == null) {
                return null
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode
    }

    fun searchForMovie(title: String): Movie? {
        return search(title.toLowerCase())?.movie
    }

    fun startsWith(title: String): List<Movie?>? {
        val node = search(title.toLowerCase()) ?: return null
        return traverse(node)
    }

    private fun traverse(nodes: MutableMap<Char, Node>?): List<Movie?>? {
        if (nodes == null) return queryResult
        nodes.keys.forEach { key ->
            val currentNode = nodes[key]
            if (currentNode != null) {
                if (currentNode.movie != null) queryResult.add(currentNode.movie!!)
                traverse(currentNode.childNodes)
            } else {
                return queryResult
            }
        }
        return queryResult
    }

    private fun traverse(original: Node): List<Movie?>? {
        queryResult = mutableListOf()
        if (original.movie != null) queryResult.add(original.movie!!)
        return traverse(original.childNodes)
    }


}