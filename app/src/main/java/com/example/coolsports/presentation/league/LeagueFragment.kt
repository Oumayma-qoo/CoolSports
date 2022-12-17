package com.example.coolsports.presentation.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolsports.R
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.databinding.FragmentLeagueBinding
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.league.LeagueModel
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.GroupList
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.presentation.base.BaseFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class LeagueFragment : BaseFragment() {

    val TAG: String = "LeagueFragment"
    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()
    var bundle = Bundle()
    var listLeague = mutableListOf<LeagueModel>()

    var leagueId: Int = 0


    private val leagueListAdapter by lazy { LeagueListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.PREMIERE_LEAGUE),
                Constants.PREMIERE_LEAGUE,"England","https://cdn1.77577cf.com/img-zq/Image/league_match/images/164577482086.png?win007=sell"
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.LA_LIGA), Constants.LA_LIGA,"Spain","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20200415071847.jpg?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.SERIE_A), Constants.SERIE_A,"Italy","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20190815175316.png?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.BUNDESLIGA), Constants.BUNDESLIGA,"Germany","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210630142615.png?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.LIGUE_1), Constants.LIGUE_1,"France","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210416104457.png?win007=sell"))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CHINESE_SUPER_LEAGUE),
                Constants.CHINESE_SUPER_LEAGUE,
                "China"
                ,
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20140111105821.jpg?win007=sell"

            ,
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AFC_CHAMPIONS_LEAGUE),
                Constants.AFC_CHAMPIONS_LEAGUE,
                "Asia",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210104160334.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.ASIAN_QUALIFIERS),
                Constants.ASIAN_QUALIFIERS,"International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210104160247.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.SOUTH_AMERICAN_QUALIFIER),
                Constants.SOUTH_AMERICAN_QUALIFIER, "International","https://cdn1.77577cf.com/img-zq/Image/league_match/images/2008571027162493.jpg?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_QUALIFIER),
                Constants.EUROPEAN_QUALIFIER,
                "International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20085710192243008.jpg?win007=sell"
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.WORLD_CUP), Constants.WORLD_CUP,"International","https://cdn1.77577cf.com/img-zq/Image/league_match/images/164885996047.png?win007=sell"))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_CUP),
                Constants.EUROPEAN_CUP,
                "International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/164885996047.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CONFEDERATIONS_CUP),
                Constants.CONFEDERATIONS_CUP,
                "Europe",

                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20170531172156.jpg?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AMERICAS_CUP),
                Constants.AMERICAS_CUP,
                "Americas",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20200411095945.jpg?win007=sell"
            )
        )
        leagueListAdapter.submitList(listLeague)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initRV()

    }

    private fun initRV() {
        binding.leagueRecyclerView.adapter = leagueListAdapter
        binding.leagueRecyclerView.layoutManager = LinearLayoutManager(activity)

        leagueListAdapter.setItemTapListener(object : LeagueListAdapter.OnItemTap {
            override fun onTap(position: Int) {
                leagueId = leagueListAdapter.getLeagueId(position)
                bundle.putInt("leagueId", leagueId)
                if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                    navController.navigate(
                        R.id.action_leagueFragment_to_leagueInfoFragment,
                        bundle
                    )
            }
        })
    }
}



