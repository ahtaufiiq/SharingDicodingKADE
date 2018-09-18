package com.example.ahmad.footbalmatch.data.repository

import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.data.response.Teams
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import io.reactivex.Flowable
import io.reactivex.Observable

class FootballRepositoryImpl(private val footballRest: FootballRest) : FootbalRepository {

    override fun getEventById(id: String): Observable<Events> = footballRest.getEventById(id)

    override fun getLastMatch(id: String): Observable<Events> = footballRest.getLastmatch(id)

    override fun getNextMatch(id: String): Observable<Events> = footballRest.getNextmatch(id)

    override fun getTeams(id: String): Observable<Teams> = footballRest.getTeam(id)
}