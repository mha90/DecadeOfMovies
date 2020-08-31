package me.mhabulazm.decadeofmovies.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import me.mhabulazm.decadeofmovies.R

class MainActivity : AppCompatActivity(), MainActivityConnector {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun addFragment(fragment: Fragment, addToBackToStack: Boolean) {
        val beginTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackToStack) beginTransaction.addToBackStack(null)
        beginTransaction.replace(R.id.fragmentContainer, fragment).commit()
    }


}
