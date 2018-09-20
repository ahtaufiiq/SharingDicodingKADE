package com.example.ahmad.footbalmatch.view.detail.detailTeam.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.view.detail.detailMatch.DetailActivity
import com.example.ahmad.footbalmatch.view.detail.detailTeam.player.playerDetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.card_player.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val teamList: List<Player>, val context: Context?) : RecyclerView.Adapter<PlayerAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.card_player, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Player) {
            itemView.tv_player.text = team.strPlayer
            itemView.tv_position.text=team.strPosition
            Glide.with(itemView.context)
                    .load(team.strCutout)
                    .into(itemView.img_player)
            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerDetailActivity>("player" to team.idPlayer)
            }


        }

    }
}