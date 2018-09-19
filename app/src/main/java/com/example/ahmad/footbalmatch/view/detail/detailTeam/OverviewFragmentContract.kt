package com.example.ahmad.footbalmatch.view.detail.detailTeam

import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.response.Team

interface OverviewFragmentContract {

    interface View {
        fun setData(team: Team)
    }

    interface Presenter {
        fun getTeams(id: String)
    }
}