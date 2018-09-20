package com.example.ahmad.footbalmatch.view.detail.detailTeam.player.playerDetail

import com.example.ahmad.footbalmatch.data.response.Player

interface PlayerDetailContract {

    interface View {
        fun setDataPlayer(player: Player)
    }

    interface Presenter {
        fun getPlayer(id: String)
    }
}