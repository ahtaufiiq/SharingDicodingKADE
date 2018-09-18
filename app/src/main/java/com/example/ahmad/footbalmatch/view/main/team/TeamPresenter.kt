package com.example.ahmad.footbalmatch.view.main.team

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.view.main.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamPresenter(private val mView: TeamContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : TeamContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getTeam() {

        compositeDisposable.add(footballRepositoryImpl.getAllTeam("4328")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataMatch(it.teams)
                })
    }

}