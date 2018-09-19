package com.example.ahmad.footbalmatch.view.detail.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
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
import com.example.ahmad.footbalmatch.data.ViewPagerAdapter
import com.example.ahmad.footbalmatch.data.local.Favorite
import com.example.ahmad.footbalmatch.data.local.FavoriteTeam
import com.example.ahmad.footbalmatch.data.local.database
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.response.Team
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import com.example.ahmad.footbalmatch.view.main.match.lastMatch.LastMatchFragment
import com.example.ahmad.footbalmatch.view.main.match.nextMatch.NextMatchFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), DetailTeamContract.View {
    override fun setDataPlayer(player: List<Player>) {
        for (name: Player in player) {
            Log.d("namanya", name.strPlayer)
        }
    }

    private lateinit var team: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id: String
    private lateinit var mPresenter: DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        mPresenter = DetailTeamPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))

        id = intent.getStringExtra("event")
        mPresenter.getTeam(id)
        mPresenter.getPlayer(id)


        favoriteState()
        val vPager = findViewById<ViewPager>(R.id.viewpager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(LastMatchFragment(), "Last Match")
        adapter.populateFragment(NextMatchFragment(), "Next Match")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)

    }

    override fun setDataEvent(team: Team) {
        this.team = team

        tv_team_detail.text = team.strTeam
        Glide.with(this)
                .load(team.strTeamBadge)
                .into(tv_teamBadge_detail)
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
                insert(Favorite.TABLE_FAVORITE_TEAM,
                        Favorite.ID to id,
                        Favorite.ID_TEAM to team.idTeam,
                        Favorite.TEAM_NAME to team.strTeam,
                        Favorite.TEAM_BADGE to team.strTeamBadge
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("Error", e.message)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE_TEAM)
                    .whereArgs("(ID_TEAM = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            isFavorite = !favorite.isEmpty()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id})",
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