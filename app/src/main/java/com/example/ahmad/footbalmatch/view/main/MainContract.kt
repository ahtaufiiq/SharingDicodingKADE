package com.example.ahmad.footbalmatch.view.main

import com.example.ahmad.footbalmatch.data.response.Event

interface MainContract {
    interface View {
        fun setDataMatch(matchList: List<Event>)
    }

    interface Presenter {
        fun getMatch(league:String)
    }
}