package com.example.ahmad.footbalmatch.view.main

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.view.main.nextMatch.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    private
    lateinit var mView: MainContract.View

    @Mock
    lateinit var footballRepositoryImpl: FootballRepositoryImpl

    @Mock
    private
    lateinit var mPresenter: MainPresenter

    lateinit var match : Events

    lateinit var footballMatch: Observable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        match = Events(event)
        footballMatch = Observable.just(match)
        mPresenter = MainPresenter(mView, footballRepositoryImpl)

        `when`(footballRepositoryImpl.getNextMatch("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getNextMatch_shouldSuccess() {
        mPresenter.getMatch()
        verify(mView).setDataMatch(event)
    }
}