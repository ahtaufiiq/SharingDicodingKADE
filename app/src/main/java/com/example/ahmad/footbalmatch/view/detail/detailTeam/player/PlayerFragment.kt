package com.example.ahmad.footbalmatch.view.detail.detailTeam.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : Fragment() , PlayerFragmentContract.View {
    override fun setDataPlayer(player: List<Player>) {
        matchLists.clear()
        matchLists.addAll(player)
        rv_player.adapter.notifyDataSetChanged()
    }
    private var matchLists: MutableList<Player> = mutableListOf()
    private lateinit var mPresenter: PlayerFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = PlayerFragmentPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        val team= arguments?.getString("idTeam")
        rv_player.layoutManager = LinearLayoutManager(context)
        rv_player.adapter = PlayerAdapter(matchLists, context)

        mPresenter.getPlayer(team!!)
    }

}
