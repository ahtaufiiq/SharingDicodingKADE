package com.example.ahmad.footbalmatch.view.lastMatch

import com.example.ahmad.footbalmatch.data.response.Event

interface LastMatchContract {
    interface View{
        fun setDataMatch(matchList: List<Event>)
    }
    interface Presenter{
        fun getMatch(league:String)
    }
}