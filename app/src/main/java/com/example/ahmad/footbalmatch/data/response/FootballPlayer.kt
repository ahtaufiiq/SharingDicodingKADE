package com.example.ahmad.footbalmatch.data.response

import com.google.gson.annotations.SerializedName


data class FootballPlayer(
    @SerializedName("player")
    var player: List<Player>)

