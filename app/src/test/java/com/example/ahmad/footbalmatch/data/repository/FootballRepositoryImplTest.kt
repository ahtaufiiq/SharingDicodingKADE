package com.example.ahmad.footbalmatch.data.repository

import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FootballRepositoryImplTest {
    @Mock
    lateinit var footballRest: FootballRest

    lateinit var matchRepositoryImpl: FootballRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchRepositoryImpl = FootballRepositoryImpl(footballRest)
    }


    @Test
    fun getEventById() {
        matchRepositoryImpl.getEventById("123")
        verify(footballRest).getEventById("123")
    }
    @Test
    fun getUpcomingMatch() {
        matchRepositoryImpl.getNextMatch("123")
        verify(footballRest).getNextmatch("123")
    }

    @Test
    fun getFootballMatch() {
        matchRepositoryImpl.getLastMatch("123")
        verify(footballRest).getLastmatch("123")
    }

    @Test
    fun getTeams() {
        matchRepositoryImpl.getTeams("123")
        verify(footballRest).getTeam("123")
    }
}