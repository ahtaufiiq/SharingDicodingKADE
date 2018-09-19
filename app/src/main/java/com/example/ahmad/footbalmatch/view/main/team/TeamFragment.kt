package com.example.ahmad.footbalmatch.view.main.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.example.ahmad.footbalmatch.R
import com.example.ahmad.footbalmatch.R.array.league
import com.example.ahmad.footbalmatch.data.repository.FootballRepositoryImpl
import com.example.ahmad.footbalmatch.data.response.Team
import com.example.ahmad.footbalmatch.data.retrofit.FootballApiService
import com.example.ahmad.footbalmatch.data.retrofit.FootballRest
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.ctx

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(), TeamContract.View {

    private var matchLists: MutableList<Team> = mutableListOf()
    lateinit var mPresenter: TeamPresenter
    private lateinit var leagueName: String
    override fun setDataMatch(matchList: List<Team>) {
        if (matchList.isNotEmpty()) {
            matchLists.clear()
            matchLists.addAll(matchList)
            rv_team.layoutManager = LinearLayoutManager(context)
            rv_team.adapter = TeamAdapter(matchLists, context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        mPresenter = TeamPresenter(this, FootballRepositoryImpl(FootballApiService.getClient().create(FootballRest::class.java)))
        mPresenter.getAllTeam("English Premier League")
        setHasOptionsMenu(true)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_team.adapter = spinnerAdapter
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_team.selectedItem.toString()
                mPresenter.getAllTeam(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.searchTeam(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                mPresenter.getTeam("4328")
                return true
            }
        })
    }

}
