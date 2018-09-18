package com.example.ahmad.footbalmatch.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.id.*
import com.example.ahmad.footbalmatch.view.main.favorite.FavoriteFragment
import com.example.ahmad.footbalmatch.view.main.lastMatch.LastMatchFragment
import com.example.ahmad.footbalmatch.view.main.nextMatch.MatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_next_match -> {
                    loadMatchFragment(savedInstanceState)
                }
                navigation_favorite -> {
                    loadFavoritesFragment(savedInstanceState)
                }
                navigation_last_match -> {
                    loadLastMatchFragment(savedInstanceState)
                }

            }
            true
        }
        navigation.selectedItemId = R.id.navigation_last_match
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }
}
