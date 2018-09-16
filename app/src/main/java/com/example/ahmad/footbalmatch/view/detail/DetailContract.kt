package com.example.ahmad.footbalmatch.view.detail

import com.example.ahmad.footbalmatch.model.response.Team

interface DetailContract {

    interface View{
        fun setLogoHomeTeam(team: Team)
        fun setLogoAwayTeam(team: Team)
    }

    interface Presenter{
        fun getLogoAwayTeam(id:String)
        fun getLogoHomeTeam(id:String)
    }
}