package com.example.ahmad.footbalmatch.view.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R.layout.card_match
import com.example.ahmad.footbalmatch.data.DateHelper
import com.example.ahmad.footbalmatch.data.dbLocal.FavoriteMatch
import kotlinx.android.synthetic.main.card_match.view.*

class FavoriteAdapter(val ctx: Context, private val mList: List<FavoriteMatch>):
        RecyclerView.Adapter<FavoriteAdapter.FavHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavHolder {
        return FavHolder(LayoutInflater.from(ctx).inflate(card_match, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavHolder, position: Int) {
        holder.bind(mList[position])
    }

    class FavHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(mFav: FavoriteMatch){

            itemView.apply {
                tv_tanggal.text = DateHelper.reformatStringDate(mFav.dateEvent,
                        DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
                tv_home_team.text = mFav.homeTeam
                away_team.text = mFav.awayTeam
                score_team_home.text = mFav.homeScore
                score_team_away.text = mFav.awayScore
            }

        }
    }


}