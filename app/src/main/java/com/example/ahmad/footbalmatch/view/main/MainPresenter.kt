package com.example.ahmad.footbalmatch.view.main

import com.example.ahmad.footbalmatch.model.repository.FootballRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(val mView: MainContract.View) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getLastMatch() {
        compositeDisposable.add(FootballRepositoryImpl().getLastMatch("4328")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataLastMatch(it.events)
                })
    }

    override fun getNextMatch() {
        compositeDisposable.add(FootballRepositoryImpl().getNextMatch("4328")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataLastMatch(it.events)
                })
    }
}