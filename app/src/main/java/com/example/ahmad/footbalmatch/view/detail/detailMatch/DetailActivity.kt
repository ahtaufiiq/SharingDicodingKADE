package com.example.ahmad.footbalmatch.view.detail.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide

import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.drawable.ic_add_to_favorites
import com.example.ahmad.footbalmatch.R.drawable.ic_added_to_favorites
import com.example.ahmad.footbalmatch.R.id.add_to_favorite
import com.example.ahmad.footbalmatch.R.menu.detail_menu
import com.example.ahmad.footbalmatch.data.DateHelper
import com.example.ahmad.footbalmatch.data.local.Favorite
import com.example.ahmad.footbalmatch.data.local.database
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.response.Team
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var event: Event
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id: String
    private lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mPresenter = DetailPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))

        id = intent.getStringExtra("event")
        mPresenter.getEvent(id)
        favoriteState()

    }

    override fun setDataEvent(team: Event) {
        event = team
        tv_tanggal_detail.text = DateHelper.reformatStringDate(team.dateEvent.toString(), DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
        tv_home_team_detail.text = team.strHomeTeam
        score_team_home_detail.text = team.intHomeScore
        tv_away_team_detail.text = team.strAwayTeam
        score_team_away_detail.text = team.intAwayScore

        goal_home.text = team.strHomeGoalDetails ?: "Data Belum Tersedia"
        goal_away.text = team.strAwayGoalDetails ?: "Data Belum Tersedia"

        shots_home.text = team.intHomeShots ?: "Data Belum Tersedia"
        shots_away.text = team.intAwayShots ?: "Data Belum Tersedia"

        gk_home.text = team.strHomeLineupGoalkeeper ?: "Data Belum Tersedia"
        gk_away.text = team.strAwayLineupGoalkeeper ?: "Data Belum Tersedia"

        defense_home.text = team.strHomeLineupDefense ?: "Data Belum Tersedia"
        defense_away.text = team.strAwayLineupDefense ?: "Data Belum Tersedia"

        midfield_home.text = team.strHomeLineupMidfield ?: "Data Belum Tersedia"
        midfield_away.text = team.strAwayLineupMidfield ?: "Data Belum Tersedia"

        forward_home.text = team.strHomeLineupForward ?: "Data Belum Tersedia"
        forward_away.text = team.strAwayLineupForward ?: "Data Belum Tersedia"
        mPresenter.getLogoAwayTeam(team.idAwayTeam)
        mPresenter.getLogoHomeTeam(team.idHomeTeam)
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID to id,
                        Favorite.ID_EVENT to event.idEvent,
                        Favorite.DATE_EVENT to event.dateEvent,
                        Favorite.HOME_TEAM to event.strHomeTeam,
                        Favorite.HOME_SCORE to event.intHomeScore,
                        Favorite.AWAY_TEAM to event.strAwayTeam,
                        Favorite.AWAY_SCORE to event.intAwayScore
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("Error", e.message)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            isFavorite = !favorite.isEmpty()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to id)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}