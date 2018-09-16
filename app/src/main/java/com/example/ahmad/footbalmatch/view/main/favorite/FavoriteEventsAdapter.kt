package com.example.ahmad.footbalmatch.view.main.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.id.*
import com.example.ahmad.footbalmatch.model.DateHelper
import com.example.ahmad.footbalmatch.model.local.Favorite
import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.view.detail.DetailActivity
import kotlinx.android.synthetic.main.card_match.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteEventsAdapter(val context: Context?, val eventList: List<Favorite>) : RecyclerView.Adapter<FavoriteEventsAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match, parent, false))
    }

    override fun getItemCount(): Int = eventList.size


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Favorite) {

            itemView.tv_home_team.text = event.teamBadge
            itemView.score_team_home.text = event.teamId
            itemView.score_team_away.text = event.teamId
            itemView.away_team.text = event.teamName

        }
    }


}