package com.example.ahmad.footbalmatch.view.main.match.lastMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import com.example.ahmad.footbalmatch.view.main.MainContract
import com.example.ahmad.footbalmatch.view.main.MatchAdapter
import kotlinx.android.synthetic.main.fragment_match.*


class LastMatchFragment : Fragment(), MainContract.View {


    private var matchLists: MutableList<Event> = mutableListOf()
    lateinit var mPresenter: LastMatchPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_match, container, false)

        mPresenter = LastMatchPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.setHasFixedSize(true)
        rv_match.adapter = MatchAdapter(context, matchLists)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerId = resources.getIntArray(R.array.id_league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_match.adapter = spinnerAdapter
        spinner_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                mPresenter.getMatch("${spinnerId[position]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun setDataMatch(matchList: List<Event>) {

        if (matchList.isNotEmpty()) {
            matchLists.clear()
            matchLists.addAll(matchList)
            rv_match.adapter.notifyDataSetChanged()
        }

    }


}
