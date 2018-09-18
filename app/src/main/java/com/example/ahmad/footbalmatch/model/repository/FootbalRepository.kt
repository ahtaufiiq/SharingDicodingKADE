package com.example.ahmad.footbalmatch.model.repository

import com.example.ahmad.footbalmatch.model.response.Events
import com.example.ahmad.footbalmatch.model.response.Teams
import io.reactivex.Observable

interface FootbalRepository {

    fun getLastMatch(id: String): Observable<Events>

    fun getNextMatch(id: String): Observable<Events>

    fun getTeams(id: String): Observable<Teams>

    fun getEventById(id: String): Observable<Events>
}