package com.example.ahmad.footbalmatch.view.lastMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.view.MatchAdapter
import kotlinx.android.synthetic.main.fragment_match.*


class LastMatchFragment : Fragment() ,LastMatchContract.View{

    lateinit var mPresenter:LastMatchPresenter


    private var matchLists: MutableList<Event> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter= LastMatchPresenter(this)

//        val match=Event("2018-11-11"
//                ,"12"
//                ,"123"
//                ,"23"
//                ,"2"
//                ,"3"
//                ,"Chelsea",
//                "Man United")
//
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)

        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.setHasFixedSize(true)
        rv_match.adapter = MatchAdapter(context, matchLists)

        mPresenter.getMatch("4328")

    }

    override fun setDataMatch(matchList: List<Event>) {

        if (matchList.isNotEmpty()) {
            matchLists.clear()
            matchLists.addAll(matchList)
            rv_match.adapter.notifyDataSetChanged()
        }

    }


}
