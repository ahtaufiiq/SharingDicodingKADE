package com.example.ahmad.footbalmatch.view.main.team

import com.example.ahmad.footbalmatch.data.response.Team

interface TeamContract {
    interface View {
        fun setDataMatch(matchList: List<Team>)
    }

    interface Presenter {
        fun getAllTeam(league:String)
        fun searchTeam(teamName: String)
    }
}