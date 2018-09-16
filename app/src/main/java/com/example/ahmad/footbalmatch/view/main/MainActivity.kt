package com.example.ahmad.footbalmatch.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.model.response.Event
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainContract.View {
    private var matchLists : MutableList<Event> = mutableListOf()

    override fun setDataLastMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        rv_match.layoutManager = LinearLayoutManager(this)
        rv_match.adapter = MatchAdapter(this, matchLists)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    lateinit var mPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter= MainPresenter(this)
        mPresenter.getLastMatch()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val ft = supportFragmentManager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_next_match -> {
                mPresenter.getNextMatch()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_last_match -> {
                mPresenter.getLastMatch()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

}
