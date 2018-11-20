package com.example.ahmad.footbalmatch.view.lastMatch

import android.util.Log
import com.example.ahmad.footbalmatch.data.retrofit.RetrofitBuilder
import com.example.ahmad.footbalmatch.data.retrofit.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LastMatchPresenter(private val mView: LastMatchContract.View): LastMatchContract.Presenter{

    override fun getMatch(league: String) {
        val retrofit= RetrofitBuilder.getClient().create(RetrofitService::class.java)
        CompositeDisposable().add(
                retrofit.getLastmatch(league)
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