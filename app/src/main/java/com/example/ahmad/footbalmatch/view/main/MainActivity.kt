package com.example.ahmad.footbalmatch.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.id.navigation_last_match
import com.example.ahmad.footbalmatch.R.id.navigation_next_match
import com.example.ahmad.footbalmatch.view.main.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                navigation_next_match -> {
                    loadTeamsFragment(savedInstanceState)
                }
                navigation_last_match -> {
                    loadFavoritesFragment(savedInstanceState)
                }

            }
            true
        }
        navigation.selectedItemId = R.id.navigation_last_match
    }
    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
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
