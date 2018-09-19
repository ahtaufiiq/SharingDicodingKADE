package com.example.ahmad.footbalmatch.data.repository

import com.example.ahmad.footbalmatch.data.response.*
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import io.reactivex.Observable

class FootballRepositoryImpl(private val footballRest: FootballRest) : FootbalRepository {
    override fun getAllTeam(id: String): Observable<Teams> = footballRest.getAllTeam(id)

    override fun getAllPlayer(id: String): Observable<FootballPlayer> = footballRest.getAllPlayers(id)

    override fun getPlayer(id: String): Observable<PlayerDetail> = footballRest.getPlayerDetail(id)

    override fun searchEvent(query: String?): Observable<SearchedMatches> = footballRest.searchEvent(query)

    override fun searchTeams(id: String): Observable<Teams> = footballRest.searchTeams(id)

    override fun getEventById(id: String): Observable<Events> = footballRest.getEventById(id)

    override fun getLastMatch(id: String): Observable<Events> = footballRest.getLastmatch(id)

    override fun getNextMatch(id: String): Observable<Events> = footballRest.getNextmatch(id)

    override fun getTeams(id: String): Observable<Teams> = footballRest.getTeam(id)
}