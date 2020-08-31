package me.mhabulazm.decadeofmovies.features.main

import androidx.fragment.app.Fragment

interface MainActivityConnector {
    fun addFragment(fragment: Fragment, addToBackToStack: Boolean = false)
}
