package com.example.ahmad.footbalmatch.data.response

import com.google.gson.annotations.SerializedName

data class PlayerDetail(
        @SerializedName("players")
        var player: List<Player>)