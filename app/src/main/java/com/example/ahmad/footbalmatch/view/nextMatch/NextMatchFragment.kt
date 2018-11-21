package com.example.ahmad.footbalmatch.view.match.nextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.data.response.Event
import com.example.ahmad.footbalmatch.data.retrofit.RetrofitBuilder
import com.example.ahmad.footbalmatch.data.retrofit.RetrofitService
import com.example.ahmad.footbalmatch.view.MatchAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_match.*


class NextMatchFragment : Fragment(){

    private var matchLists: MutableList<Event> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val match=Event("2018-11-11"
//                ,"12"
//                ,"123"
//                ,"23"
//                ,null
//                ,null
//                ,"Chelsea",
//                "Man United")
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)
//        matchLists.add(match)

        rv_match.layoutManager = LinearLayoutManager(context)
        rv_match.setHasFixedSize(true)
        rv_match.adapter = MatchAdapter(context, matchLists){
            //
        }

        val retrofit= RetrofitBuilder.getClient().create(RetrofitService::class.java)
        CompositeDisposable().add(
                retrofit.getNextmatch("4328")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    setDataMatch(it.events)
                                }, { error ->
                            Log.e("Error", error.message)
                        })
        )
    }

    private fun setDataMatch(matchList: List<Event>) {
        if (matchList.isNotEmpty()) {
            matchLists.clear()
            matchLists.addAll(matchList)
            rv_match.adapter.notifyDataSetChanged()
        }
    }


}
