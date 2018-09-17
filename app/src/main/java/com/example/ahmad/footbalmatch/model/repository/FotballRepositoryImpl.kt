package com.example.ahmad.footbalmatch.model.repository

import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.model.response.Events
import com.example.ahmad.footbalmatch.model.response.Teams
import com.example.ahmad.footbalmatch.model.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.model.retrofit.FootballRest
import io.reactivex.Observable

class FootballRepositoryImpl: FootbalRepository {

    override fun getEventById(id: String): Observable<Events> = FootballApiService.getClient().create(FootballRest::class.java).getEventById(id)

    override fun getLastMatch(id: String): Observable<Events> = FootballApiService.getClient().create(FootballRest::class.java).getLastmatch(id)

    override fun getNextMatch(id: String): Observable<Events> = FootballApiService.getClient().create(FootballRest::class.java).getNextmatch(id)

    override fun getTeams(id: String): Observable<Teams> = FootballApiService.getClient().create(FootballRest::class.java).getTeam(id)
}