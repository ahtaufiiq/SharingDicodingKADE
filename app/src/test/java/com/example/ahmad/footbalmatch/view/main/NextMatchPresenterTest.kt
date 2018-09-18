package com.example.ahmad.footbalmatch.view.main

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.view.main.match.nextMatch.NextMatchPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private
    lateinit var mView: MainContract.View

    @Mock
    lateinit var footballRepositoryImpl: FootballRepositoryImpl

    @Mock
    private
    lateinit var mPresenter: NextMatchPresenter

    private lateinit var match: Events

    private lateinit var matchList: Observable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        match = Events(event)
        matchList = Observable.just(match)
        mPresenter = NextMatchPresenter(mView, footballRepositoryImpl)

        `when`(footballRepositoryImpl.getNextMatch("4328")).thenReturn(matchList)
    }

    @Test
    fun getNextMatch_shouldSuccess() {
        mPresenter.getMatch()
        Thread.sleep(2000)
        verify(mView).setDataMatch(event)
    }
}