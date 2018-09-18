package com.example.ahmad.footbalmatch.data.retrofit

import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.data.response.Teams
import io.reactivex.Flowable
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
}