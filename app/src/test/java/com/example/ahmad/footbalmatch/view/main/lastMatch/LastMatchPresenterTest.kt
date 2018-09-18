package com.example.ahmad.footbalmatch.view.main.lastMatch

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Events
import com.example.ahmad.footbalmatch.view.main.MainContract
import com.example.ahmad.footbalmatch.view.main.match.lastMatch.LastMatchPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before

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

    lateinit var match: Events

    lateinit var matchList: Observable<Events>

    private val event = mutableListOf<Event>()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        match = Events(event)
        matchList = Observable.just(match)
        mPresenter = LastMatchPresenter(mView, footballRepositoryImpl)

        Mockito.`when`(footballRepositoryImpl.getLastMatch("4328")).thenReturn(matchList)
    }

    @Test
    fun getLastMatch_shouldSuccess() {
        mPresenter.getMatch()
        Thread.sleep(2000)
        Mockito.verify(mView).setDataMatch(event)
    }
}