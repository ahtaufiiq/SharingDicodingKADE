package com.example.ahmad.footbalmatch.view.main.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.model.local.Favorite
import com.example.ahmad.footbalmatch.model.local.database
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class FavoriteFragment : Fragment() {
    private lateinit var listEvent: RecyclerView
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteEventsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        showFavorite()
        super.onActivityCreated(savedInstanceState)
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)

            Log.d("asd",favorite.size.toString())
            adapter = FavoriteEventsAdapter(context,favorites)
            rv_favorite.layoutManager = LinearLayoutManager(context)
            rv_favorite.adapter = adapter
        }
    }
}
