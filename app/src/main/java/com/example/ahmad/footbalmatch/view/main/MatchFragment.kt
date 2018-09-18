package com.example.ahmad.footbalmatch.view.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.model.local.Favorite
import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.view.main.favorite.FavoriteEventsAdapter
import kotlinx.android.synthetic.main.fragment_match.*


class MatchFragment : Fragment(), MainContract.View {


    private var matchLists: MutableList<Event> = mutableListOf()
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteEventsAdapter

    lateinit var mPresenter: MainPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_match, container, false)
        mPresenter = MainPresenter(this)
        mPresenter.getNextMatch()
        return view
    }

    override fun setDataLastMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.adapter = MatchAdapter(context, matchLists)

    }


}
