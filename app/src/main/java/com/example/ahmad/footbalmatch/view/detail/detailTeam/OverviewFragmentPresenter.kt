package com.example.ahmad.footbalmatch.view.detail.detailTeam

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.view.detail.detailMatch.DetailTeamContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OverviewFragmentPresenter(private val mView: OverviewFragmentContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : OverviewFragmentContract.Presenter {
    override fun getTeams(id: String) {
        compositeDisposable.add(footballRepositoryImpl.getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setData(it.teams[0])
                })
    }


    private val compositeDisposable = CompositeDisposable()


}