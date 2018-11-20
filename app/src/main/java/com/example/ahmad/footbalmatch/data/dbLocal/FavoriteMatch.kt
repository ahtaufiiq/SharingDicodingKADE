package com.example.ahmad.footbalmatch.data.dbLocal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMatch (val id: Long?, val idEvent: String, val homeTeam: String, val awayTeam: String, val homeScore: String?, val awayScore: String?, val dateEvent: String): Parcelable {

    companion object {
        const val TABLE_FAV_MATCH : String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID"

        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_DATE: String = "EVENT_DATE"

    }

}