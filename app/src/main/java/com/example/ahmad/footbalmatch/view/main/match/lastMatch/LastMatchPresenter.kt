package com.example.ahmad.footbalmatch.view.main.match.lastMatch

import android.util.Log
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.view.main.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LastMatchPresenter(private val mView: MainContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getMatch(league: String) {

        compositeDisposable.add(footballRepositoryImpl.getLastMatch(league)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            mView.setDataMatch(it.events)
                        }, { error ->
                    Log.e("Error", error.message)
                })
        )
    }

}