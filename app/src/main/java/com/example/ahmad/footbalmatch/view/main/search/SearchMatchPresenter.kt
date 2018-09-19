package com.example.ahmad.footbalmatch.view.main.search

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class SearchMatchPresenter(val mView: SearchMatchContract.View,
                           val footballRepositoryImpl: FootballRepositoryImpl) : SearchMatchContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatchs(query: String?) {
        compositeDisposable.add(footballRepositoryImpl.searchEvent(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.displayMatch(it.events ?: Collections.emptyList())
                })
    }

}