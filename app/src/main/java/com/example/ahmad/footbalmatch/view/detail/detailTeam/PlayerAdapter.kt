package com.example.ahmad.footbalmatch.view.detail.detailTeam

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.response.Team
import com.example.ahmad.footbalmatch.view.detail.detailMatch.DetailTeamActivity
import kotlinx.android.synthetic.main.card_team.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val teamList: List<Player>, val context: Context?) : RecyclerView.Adapter<PlayerAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.card_team, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Player) {
            itemView.tvTeam.text = team.strPlayer
            Glide.with(itemView.context)
                    .load(team.strThumb)
                    .into(itemView.imgTeam)



        }

    }
}