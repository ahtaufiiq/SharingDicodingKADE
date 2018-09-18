package com.example.ahmad.footbalmatch.view.main.lastMatch

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.view.main.MainContract
import com.example.ahmad.footbalmatch.view.main.nextMatch.MainPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    @Mock
    private
    lateinit var mView: MainContract.View

    @Mock
    lateinit var footballRepositoryImpl: FootballRepositoryImpl

    @Mock
    private
    lateinit var mPresenter: LastMatchPresenter

    lateinit var match : Events

    lateinit var footballMatch: Observable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        match = Events(event)
        footballMatch = Observable.just(match)
        mPresenter = LastMatchPresenter(mView, footballRepositoryImpl)

        Mockito.`when`(footballRepositoryImpl.getLastMatch("4328")).thenReturn(footballMatch)
    }

    @Test
    fun getLastMatch_shouldSuccess() {
        mPresenter.getMatch()
        Mockito.verify(mView).setDataMatch(event)
    }
}