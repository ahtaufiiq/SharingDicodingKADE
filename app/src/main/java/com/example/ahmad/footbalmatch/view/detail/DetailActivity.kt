package com.example.ahmad.footbalmatch.view.detail

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
import com.example.ahmad.footbalmatch.model.DateHelper
import com.example.ahmad.footbalmatch.model.local.Favorite
import com.example.ahmad.footbalmatch.model.local.database
import com.example.ahmad.footbalmatch.model.response.Event
import com.example.ahmad.footbalmatch.model.response.Team
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
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
        mPresenter = DetailPresenter(this)


        event = intent.getParcelableExtra("event")

        id=event.idEvent
        favoriteState()

        mPresenter.getLogoAwayTeam(event.idAwayTeam)
        mPresenter.getLogoHomeTeam(event.idHomeTeam)
        setData(event)
    }

    private fun setData(event: Event) {

        tv_tanggal_detail.text = DateHelper.reformatStringDate(event.dateEvent.toString(), DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
        tv_home_team_detail.text = event.strHomeTeam
        score_team_home_detail.text = event.intHomeScore
        tv_away_team_detail.text = event.strAwayTeam
        score_team_away_detail.text = event.intAwayScore

        goal_home.text = event.strHomeGoalDetails ?: "Data Belum Tersedia"
        goal_away.text = event.strAwayGoalDetails ?: "Data Belum Tersedia"

        shots_home.text = event.intHomeShots ?: "Data Belum Tersedia"
        shots_away.text = event.intAwayShots ?: "Data Belum Tersedia"

        gk_home.text = event.strHomeLineupGoalkeeper ?: "Data Belum Tersedia"
        gk_away.text = event.strAwayLineupGoalkeeper ?: "Data Belum Tersedia"

        defense_home.text = event.strHomeLineupDefense ?: "Data Belum Tersedia"
        defense_away.text = event.strAwayLineupDefense ?: "Data Belum Tersedia"

        midfield_home.text = event.strHomeLineupMidfield ?: "Data Belum Tersedia"
        midfield_away.text = event.strAwayLineupMidfield ?: "Data Belum Tersedia"

        forward_home.text = event.strHomeLineupForward ?: "Data Belum Tersedia"
        forward_away.text = event.strAwayLineupForward ?: "Data Belum Tersedia"

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
                        Favorite.TEAM_ID to id,
                        Favorite.TEAM_NAME to event.strAwayTeam,
                        Favorite.TEAM_BADGE to event.strHomeTeam
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("Error", e.message)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            isFavorite = !favorite.isEmpty()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
        } catch (e: SQLiteConstraintException){
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}