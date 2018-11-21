package com.example.ahmad.footbalmatch.view

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.view.favorite.FavoriteFragment
import com.example.ahmad.footbalmatch.view.lastMatch.LastMatchFragment
import com.example.ahmad.footbalmatch.view.match.nextMatch.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager(viewpager)
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(LastMatchFragment(), "List Match")
        adapter.populateFragment(FavoriteFragment(), "Favorite Match")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}
