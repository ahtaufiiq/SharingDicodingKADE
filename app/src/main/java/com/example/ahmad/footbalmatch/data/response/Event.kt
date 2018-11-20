package com.example.ahmad.footbalmatch.data.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        @SerializedName("dateEvent") var dateEvent: String?,
        @SerializedName("idAwayTeam") var idAwayTeam: String,
        @SerializedName("idEvent") var idEvent: String,
        @SerializedName("idHomeTeam") var idHomeTeam: String,
        @SerializedName("intAwayScore") var intAwayScore: String?,
        @SerializedName("intHomeScore") var intHomeScore: String?,
        @SerializedName("strHomeTeam") var strHomeTeam: String?,
        @SerializedName("strAwayTeam") var strAwayTeam: String?
) : Parcelable
