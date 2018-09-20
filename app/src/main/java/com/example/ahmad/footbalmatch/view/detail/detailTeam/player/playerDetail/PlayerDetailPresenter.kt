package com.example.ahmad.footbalmatch.view.detail.detailTeam.player.playerDetail

import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.view.detail.detailTeam.player.PlayerFragmentContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayerDetailPresenter(private val mView: PlayerDetailContract.View, private val footballRepositoryImpl: FootballRepositoryImpl) : PlayerDetailContract.Presenter {
    override fun getPlayer(id: String) {
        compositeDisposable.add(footballRepositoryImpl.getPlayer(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mView.setDataPlayer(it.player[0])
                })
    }

    private val compositeDisposable = CompositeDisposable()


}