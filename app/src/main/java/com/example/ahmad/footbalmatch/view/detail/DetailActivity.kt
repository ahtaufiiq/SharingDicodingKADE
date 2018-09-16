package com.example.ahmad.footbalmatch.view.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.model.DateHelper
import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.model.response.Team
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var event: Event

    private lateinit var mPresenter : DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mPresenter = DetailPresenter(this)

        event = intent.getParcelableExtra("event")
        mPresenter.getLogoAwayTeam(event.idAwayTeam)
        mPresenter.getLogoHomeTeam(event.idHomeTeam)
        setData(event)
    }

    private fun setData(event: Event){

        tv_tanggal_detail.text = DateHelper.reformatStringDate(event.dateEvent.toString(), DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
        tv_home_team_detail.text = event.strHomeTeam
        score_team_home_detail.text = event.intHomeScore
        tv_away_team_detail.text = event.strAwayTeam
        score_team_away_detail.text = event.intAwayScore

        goal_home.text = event.strHomeGoalDetails?:"Data Belum Tersedia"
        goal_away.text= event.strAwayGoalDetails?:"Data Belum Tersedia"

        shots_home.text=event.intHomeShots?:"Data Belum Tersedia"
        shots_away.text=event.intAwayShots?:"Data Belum Tersedia"

        gk_home.text = event.strHomeLineupGoalkeeper?:"Data Belum Tersedia"
        gk_away.text = event.strAwayLineupGoalkeeper?:"Data Belum Tersedia"

        defense_home.text = event.strHomeLineupDefense?:"Data Belum Tersedia"
        defense_away.text = event.strAwayLineupDefense?:"Data Belum Tersedia"

        midfield_home.text = event.strHomeLineupMidfield?:"Data Belum Tersedia"
        midfield_away.text = event.strAwayLineupMidfield?:"Data Belum Tersedia"

        forward_home.text = event.strHomeLineupForward?:"Data Belum Tersedia"
        forward_away.text = event.strAwayLineupForward?:"Data Belum Tersedia"

    }

    override fun setLogoAwayTeam(team: Team) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .into(img_team_away)
    }

    override fun setLogoHomeTeam(team: Team) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .into(img_team_home)
    }
}