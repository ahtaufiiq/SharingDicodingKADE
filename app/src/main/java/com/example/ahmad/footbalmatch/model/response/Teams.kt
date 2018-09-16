package com.example.ahmad.footbalmatch.model.response

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("teams")
        var teams: List<Team>)