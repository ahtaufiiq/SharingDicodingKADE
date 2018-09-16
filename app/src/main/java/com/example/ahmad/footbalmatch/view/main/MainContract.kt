package com.example.ahmad.footbalmatch.view.main

import com.example.ahmad.footbalmatch.model.response.Event

interface MainContract {
    interface View {
        fun setDataLastMatch(matchList: List<Event>)

    }

    interface Presenter {
        fun getLastMatch()
        fun getNextMatch()

    }
}