package com.example.ahmad.footbalmatch.view.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R.layout.fragment_fav
import com.example.ahmad.footbalmatch.data.dbLocal.FavoriteMatch
import com.example.ahmad.footbalmatch.data.dbLocal.db
import kotlinx.android.synthetic.main.fragment_fav.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteFragment : Fragment() {

    private var fav: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_fav.layoutManager = LinearLayoutManager(ctx)
        rv_fav.setHasFixedSize(true)
        adapter = FavoriteAdapter(ctx, fav)
        rv_fav.adapter = adapter

        swipeRefresh.onRefresh {
            fav.clear()
            showFav()
        }

    }

    override fun onStart() {
        super.onStart()

        showFav()

    }

    private fun showFav(){
        context?.db?.use {
            swipeRefresh.isRefreshing = false
            val res = select(FavoriteMatch.TABLE_FAV_MATCH)
            val favv = res.parseList(classParser<FavoriteMatch>())
            fav.addAll(favv)
            rv_fav.adapter?.notifyDataSetChanged()

        }
    }

}