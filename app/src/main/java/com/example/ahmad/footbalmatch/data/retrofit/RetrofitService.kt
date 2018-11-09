package com.example.ahmad.footbalmatch.data.retrofit

import com.example.ahmad.footbalmatch.data.response.Events
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id: String): Observable<Events>

    @GET("eventsnextleague.php")
    fun getNextmatch(@Query("id") id: String): Observable<Events>
}