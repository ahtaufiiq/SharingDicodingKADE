package com.example.ahmad.footbalmatch.view.detail.detailTeam.player

import com.example.ahmad.footbalmatch.data.response.Player

interface PlayerFragmentContract {

    interface View {
        fun setDataPlayer(player: List<Player>)
    }

    interface Presenter {
        fun getPlayer(id: String)
    }
}