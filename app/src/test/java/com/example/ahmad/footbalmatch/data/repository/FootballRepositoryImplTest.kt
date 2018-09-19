package com.example.ahmad.footbalmatch.data.repository

import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FootballRepositoryImplTest {
    @Mock
    lateinit var footballRest: FootballRest

    private lateinit var footballRepositoryImpl: FootballRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        footballRepositoryImpl = FootballRepositoryImpl(footballRest)
    }


    @Test
    fun getEventById() {
        footballRepositoryImpl.getEventById("576518")
        verify(footballRest).getEventById("576518")
    }
    @Test
    fun getNextMatch() {
        footballRepositoryImpl.getNextMatch("4328")
        verify(footballRest).getNextmatch("4328")
    }

    @Test
    fun getLastMatch() {
        footballRepositoryImpl.getLastMatch("4328")
        verify(footballRest).getLastmatch("4328")
    }

    @Test
    fun getTeams() {
        footballRepositoryImpl.getTeams("133612")
        verify(footballRest).getTeam("133612")
    }

    @Test
    fun searchEvent() {
        footballRepositoryImpl.searchEvent("Arsenal")
        verify(footballRest).searchEvent("Arsenal")
    }
}