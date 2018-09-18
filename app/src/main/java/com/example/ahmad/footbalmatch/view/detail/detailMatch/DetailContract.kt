package com.example.ahmad.footbalmatch.view.detail.detailMatch

import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Team

interface DetailContract {

    interface View {
        fun setLogoHomeTeam(team: Team)
        fun setLogoAwayTeam(team: Team)
        fun setDataEvent(team: Event)

    }

    interface Presenter {
        fun getLogoAwayTeam(id: String)
        fun getLogoHomeTeam(id: String)
        fun getEvent(id: String)
    }
}