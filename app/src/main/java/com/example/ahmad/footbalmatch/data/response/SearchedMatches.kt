package com.example.ahmad.footbalmatch.data.response

import com.google.gson.annotations.SerializedName


data class SearchedMatches(
        @SerializedName("event") var events: List<Event>
)