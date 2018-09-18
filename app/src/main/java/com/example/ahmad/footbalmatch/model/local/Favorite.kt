package com.example.ahmad.footbalmatch.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(val id: Long?, val idEvent: String, val dateEvent: String?, val strHomeTeam: String?, val intHomeScore: String?, val intAwayScore: String?, val strAwayTeam: String?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_TEAM: String = "AWAY_TEAM"
    }
}