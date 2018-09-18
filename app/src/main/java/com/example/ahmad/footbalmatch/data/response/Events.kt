package com.example.ahmad.footbalmatch.data.response

import com.google.gson.annotations.SerializedName

data class Events(
        @SerializedName("events") var events: List<Event>)