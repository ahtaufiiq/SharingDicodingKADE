package com.example.ahmad.footbalmatch.view.detail.detailTeam

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.view.detail.detailMatch.DetailTeamContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayerFragmentPresenter(private val mView: PlayerFragmentContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : PlayerFragmentContract.Presenter {
    override fun getPlayer(id: String) {
        compositeDisposable.add(footballRepositoryImpl.getAllPlayer(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataPlayer(it.player)
                })
    }

    private val compositeDisposable = CompositeDisposable()


}