package com.example.ahmad.footbalmatch.view.detail.detailTeam.player.playerDetail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Player
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import com.example.ahmad.footbalmatch.view.detail.detailMatch.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() ,PlayerDetailContract.View{

    lateinit var player:Player
    override fun setDataPlayer(player: Player) {
            this.player=player
        Glide.with(this).load(player.strFanart1).into(img_banner_player)
        tv_height.text=player.strHeight?:"Tidak ada data"
        tv_weight.text=player.strWeight?:"Tidak ada data"
        tv_position_player.text=player.strPosition?:"Tidak ada data"
        tv_description_player.text=player.strDescriptionEN
    }
    private lateinit var mPresenter: PlayerDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        mPresenter = PlayerDetailPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        mPresenter.getPlayer(intent.getStringExtra("player"))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intents= Intent(this,DetailTeamActivity::class.java)
        intents.putExtra("event",player.idTeam)
        startActivity(intents)
    }
}
