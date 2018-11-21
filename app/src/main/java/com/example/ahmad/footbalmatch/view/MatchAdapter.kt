package com.example.ahmad.footbalmatch.view

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.layout.item_match
import com.example.ahmad.footbalmatch.data.DateHelper
import com.example.ahmad.footbalmatch.data.dbLocal.FavoriteMatch
import com.example.ahmad.footbalmatch.data.dbLocal.db
import com.example.ahmad.footbalmatch.data.response.Event
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick

class MatchAdapter(val context: Context?, private val matchList: List<Event>,  private val listener: (Event) -> Unit) : RecyclerView.Adapter<MatchAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(context).inflate(item_match, parent, false))
    }

    override fun getItemCount(): Int = matchList.size


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(matchList[position], listener)
        holder.favoriteState(matchList[position])
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var isFavorite: Boolean = false

        fun bind(event: Event, listener: (Event) -> Unit) {

            itemView.apply {
                tv_tanggal.text = DateHelper.reformatStringDate(event.dateEvent.toString(), DateHelper.DATE_FORMAT_YEAR_FIRST, DateHelper.DATE_FORMAT_FULL_DATE)
                tv_home_team.text = event.strHomeTeam
                score_team_home.text = event.intHomeScore
                score_team_away.text = event.intAwayScore
                away_team.text = event.strAwayTeam
                img_fav.onClick {
                    if (isFavorite) removeFromFav(event) else addToFav(event)

                    isFavorite = !isFavorite
                    setFavorite()
                }
            }

        }

        fun favoriteState(event: Event){
            itemView.context.db.use{
                val res = select(FavoriteMatch.TABLE_FAV_MATCH)
                        .whereArgs("(EVENT_ID = {id})",
                                "id" to event.idEvent)
                val favv = res.parseList(classParser<FavoriteMatch>())
                if (!favv.isEmpty()) isFavorite = true
            }
        }

        fun addToFav(event: Event){
            try{
                itemView.context!!.db.use{
                    insert(FavoriteMatch.TABLE_FAV_MATCH,
                            FavoriteMatch.ID to event.idEvent,
                            FavoriteMatch.EVENT_ID to event.idEvent,
                            FavoriteMatch.EVENT_DATE to event.dateEvent,
                            FavoriteMatch.HOME_TEAM_NAME to event.strHomeTeam,
                            FavoriteMatch.AWAY_TEAM_NAME to event.strAwayTeam,
                            FavoriteMatch.HOME_SCORE to event.intHomeScore,
                            FavoriteMatch.AWAY_SCORE to event.intAwayScore)
                }
                snackbar(itemView.cardView, "Added to favorite").show()
            }catch (e: SQLiteConstraintException){
                snackbar(itemView.cardView, e.localizedMessage).show()
            }
        }

        fun removeFromFav(event: Event){
            try{
                itemView.context.db.use{
                    delete(FavoriteMatch.TABLE_FAV_MATCH,
                            "(EVENT_ID = {id})",
                            "id" to event.idEvent)
                }
                snackbar(itemView.cardView, "Removed from favorite").show()
            }catch (e: SQLiteConstraintException){
                snackbar(itemView.cardView, e.localizedMessage).show()
            }
        }

        fun setFavorite() {
            if (isFavorite)
                itemView.img_fav.setImageResource(R.drawable.ic_added_to_favorites)
            else
                itemView.img_fav.setImageResource(R.drawable.ic_add_to_favorites)
        }
    }


}
