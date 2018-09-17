package com.example.ahmad.footbalmatch.view.detail

import com.example.ahmad.footbalmatch.model.repository.FootballRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val mView: DetailContract.View) : DetailContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun getLogoHomeTeam(id: String) {
        compositeDisposable.add(FootballRepositoryImpl().getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setLogoHomeTeam(it.teams[0])
                })
    }


    override fun getLogoAwayTeam(id: String) {
        compositeDisposable.add(FootballRepositoryImpl().getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setLogoAwayTeam(it.teams[0])
                })
    }

    override fun getEvent(id: String) {
        compositeDisposable.add(FootballRepositoryImpl().getEventById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataEvent(it.events[0])
                })
    }
}