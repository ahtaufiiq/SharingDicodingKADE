package com.example.ahmad.footbalmatch.view.main.search

import android.util.Log
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class SearchMatchPresenter(val mView: SearchMatchContract.View,
                           val footballRepositoryImpl: FootballRepositoryImpl) : SearchMatchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun searchMatchs(query: String?) {
        compositeDisposable.add(footballRepositoryImpl.searchEvent(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe (
                        {
                            mView.displayMatch(it.events)
                        }, { error ->
                    Log.e("Error", error.message)
                })
        )
    }

}