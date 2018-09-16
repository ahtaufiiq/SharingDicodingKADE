package com.example.ahmad.footbalmatch.view.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.model.local.Favorite
import com.example.ahmad.footbalmatch.model.local.database
import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.view.main.favorite.FavoriteEventsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment(),MainContract.View {
    private var matchLists : MutableList<Event> = mutableListOf()
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteEventsAdapter

    lateinit var mPresenter : MainPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_match, container, false)
        mPresenter= MainPresenter(this)
        mPresenter.getLastMatch()
        return view
    }
    override fun setDataLastMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = MatchAdapter(context, matchLists)

    }



}
