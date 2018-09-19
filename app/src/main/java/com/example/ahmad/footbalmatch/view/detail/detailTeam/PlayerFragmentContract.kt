package com.example.ahmad.footbalmatch.view.detail.detailTeam

import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.response.Team

interface PlayerFragmentContract {

    interface View {
        fun setDataPlayer(player: List<Player>)
    }

    interface Presenter {
        fun getPlayer(id: String)
    }
}