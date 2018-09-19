package com.example.ahmad.footbalmatch.data.retrofit

import com.example.ahmad.footbalmatch.data.response.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballRest {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id: String): Observable<Events>

    @GET("eventsnextleague.php")
    fun getNextmatch(@Query("id") id: String): Observable<Events>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Observable<Teams>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id: String): Observable<Events>

    @GET("searchevents.php")
    fun searchEvent(@Query("e") query: String?): Observable<SearchedMatches>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") id: String): Observable<Teams>

    @GET("search_all_teams.php")
    fun getAllTeam(@Query("l") id: String): Observable<Teams>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id: String?): Observable<FootballPlayer>

    @GET("lookupplayer.php")
    fun getPlayerDetail(@Query("id") id: String?): Observable<PlayerDetail>
}