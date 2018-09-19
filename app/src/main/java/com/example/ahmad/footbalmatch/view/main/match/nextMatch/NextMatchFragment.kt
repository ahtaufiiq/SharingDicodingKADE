package com.example.ahmad.footbalmatch.view.main.match.nextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.array.league
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import com.example.ahmad.footbalmatch.view.main.MainContract
import com.example.ahmad.footbalmatch.view.main.MatchAdapter
import kotlinx.android.synthetic.main.fragment_match.*


class NextMatchFragment : Fragment(), MainContract.View {

    private var matchLists: MutableList<Event> = mutableListOf()
    private lateinit var leagueName: String

    lateinit var mPresenter: NextMatchPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        mPresenter = NextMatchPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_match.adapter = spinnerAdapter
        spinner_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_match.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> mPresenter.getMatch("4328")
                    "English League Championship" -> mPresenter.getMatch("4329")
                    "German Bundesliga" -> mPresenter.getMatch("4331")
                    "Italian Serie A" -> mPresenter.getMatch("4332")
                    "French Ligue 1" -> mPresenter.getMatch("4334")
                    "Spanish La Liga" -> mPresenter.getMatch("4335")
                    else -> mPresenter.getMatch("4328")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun setDataMatch(matchList: List<Event>) {
        if (matchList.isNotEmpty()) {
            matchLists.clear()
            matchLists.addAll(matchList)
            rv_match.layoutManager = LinearLayoutManager(context)
            rv_match.adapter = MatchAdapter(context, matchLists)
        }
    }


}
