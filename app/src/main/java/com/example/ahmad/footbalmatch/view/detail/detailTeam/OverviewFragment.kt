package com.example.ahmad.footbalmatch.view.detail.detailTeam


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Team
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import kotlinx.android.synthetic.main.fragment_overview.*



class OverviewFragment : Fragment() ,OverviewFragmentContract.View{
    override fun setData(team: Team) {
        tv_team.text=team.strDescriptionEN
    }
    private lateinit var mPresenter: OverviewFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = OverviewFragmentPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        val team= arguments?.getString("idTeam")
        mPresenter.getTeams(team!!)
    }
}
