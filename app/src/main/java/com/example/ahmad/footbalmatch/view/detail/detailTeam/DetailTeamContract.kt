package com.example.ahmad.footbalmatch.view.detail.detailMatch

import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.response.Team

interface DetailTeamContract {

    interface View {
        fun setDataEvent(team: Team)
        fun setDataPlayer(player: List<Player>)
    }

    interface Presenter {
        fun getTeam(id: String)
        fun getPlayer(id: String)
    }
}