package com.example.ahmad.footbalmatch.data.repository

import com.example.ahmad.footbalmatch.data.response.*
import io.reactivex.Observable

interface FootbalRepository {

    fun getLastMatch(id: String): Observable<Events>

    fun getNextMatch(id: String): Observable<Events>

    fun getTeams(id: String): Observable<Teams>

    fun getEventById(id: String): Observable<Events>

    fun searchEvent(id:String): Observable<Events>

    fun searchTeams(id:String): Observable<Teams>

    fun getAllTeam(id:String): Observable<Teams>

    fun getAllPlayer(id:String): Observable<FootballPlayer>

    fun getPlayer(id:String): Observable<PlayerDetail>
}