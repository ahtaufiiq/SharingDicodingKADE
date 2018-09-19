package com.example.ahmad.footbalmatch.view.main.search

import com.example.ahmad.footbalmatch.data.response.Event

interface SearchMatchContract {

    interface View {
        fun displayMatch(matchList: List<Event>)
    }

    interface Presenter {
        fun searchMatchs(query: String?)
    }
}