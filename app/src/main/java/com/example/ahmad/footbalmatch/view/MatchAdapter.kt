package com.example.ahmad.footbalmatch.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.DateHelper
import com.example.ahmad.footbalmatch.data.response.Event
import kotlinx.android.synthetic.main.card_match.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MatchAdapter(val context: Context?, private val matchList: List<Event>,  private val listener: (Event) -> Unit) : RecyclerView.Adapter<MatchAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = matchList.size


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(matchList[position], listener)
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, listener: (Event) -> Unit) {

            itemView.apply {
                tv_tanggal.text = DateHelper.reformatStringDate(event.dateEvent.toString(), DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
                tv_home_team.text = event.strHomeTeam
                score_team_home.text = event.intHomeScore
                score_team_away.text = event.intAwayScore
                away_team.text = event.strAwayTeam
            }

            itemView.onClick { listener(event) }

        }
    }


}
