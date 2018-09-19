package com.example.ahmad.footbalmatch.view.main.team

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamPresenter(private val mView: TeamContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : TeamContract.Presenter {
    override fun getAllTeam(league: String) {
        compositeDisposable.add(footballRepositoryImpl.getAllTeam(league)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataMatch(it.teams)
                })
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getTeam(id: String) {

        compositeDisposable.add(footballRepositoryImpl.getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataMatch(it.teams)
                })
    }

    override fun searchTeam(teamName: String) {
        compositeDisposable.add(footballRepositoryImpl.searchTeams(teamName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataMatch(it.teams)
                })
    }

}