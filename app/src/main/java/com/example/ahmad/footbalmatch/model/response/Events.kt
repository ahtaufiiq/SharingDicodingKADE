package com.example.ahmad.footbalmatch.model.response

import com.google.gson.annotations.SerializedName

data class Events (
        @SerializedName("events") var events: List<Event>)