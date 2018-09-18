package com.example.ahmad.footbalmatch.data.response

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("teams")
        var teams: List<Team>)