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
    var leagueInfoList = ArrayList<LeagueData01>()
    var listLeague = mutableListOf<LeagueModel>()
    var list = mutableListOf<LeagueModel>()
    var listRules = ArrayList<LeagueData04>()
    var leagueStandingGroup = ArrayList<LeagueStandingsGroupBase>()

    var leagueId:Int = 0


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
                Constants.PREMIERE_LEAGUE
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.LA_LIGA), Constants.LA_LIGA))
        listLeague.add(LeagueModel(resources.getString(R.string.SERIE_A), Constants.SERIE_A))
        listLeague.add(LeagueModel(resources.getString(R.string.BUNDESLIGA), Constants.BUNDESLIGA))
        listLeague.add(LeagueModel(resources.getString(R.string.LIGUE_1), Constants.LIGUE_1))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CHINESE_SUPER_LEAGUE),
                Constants.CHINESE_SUPER_LEAGUE
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AFC_CHAMPIONS_LEAGUE),
                Constants.AFC_CHAMPIONS_LEAGUE
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.ASIAN_QUALIFIERS),
                Constants.ASIAN_QUALIFIERS
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.SOUTH_AMERICAN_QUALIFIER),
                Constants.SOUTH_AMERICAN_QUALIFIER
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_QUALIFIER),
                Constants.EUROPEAN_QUALIFIER
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.WORLD_CUP), Constants.WORLD_CUP))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_CUP),
                Constants.EUROPEAN_CUP
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CONFEDERATIONS_CUP),
                Constants.CONFEDERATIONS_CUP
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AMERICAS_CUP),
                Constants.AMERICAS_CUP
            )
        )

        initObserver()

        lifecycleScope.launch {
            for (league in listLeague) {
                viewModel.getLeagueInfo(league.leagueId!!, " ", 0)
            }
        }
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
                Log.d(TAG, "leagueId========= $leagueId")

                bundle.putInt("leagueId", leagueId)
                if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                    navController.navigate(
                        R.id.action_leagueFragment_to_leagueInfoFragment,
                        bundle
                    )
            }
        })
    }


    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED

        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun handleState(state: LeagueStateScreen) {
        when (state) {
            is LeagueStateScreen.IsLoading -> handleIsLoadingState(state.isLoading)
            is LeagueStateScreen.Response -> handleLeagueResponse(state.leagueInfo)
            is LeagueStateScreen.NoInternetException -> handleNetworkFailure(state.message)
            is LeagueStateScreen.GeneralException -> handleException(state.message)
            is LeagueStateScreen.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private fun handleLeagueResponse(response: BaseLeagueInfo) {

        leagueInfoList.addAll(response.leagueData01)

        handleLeagueInfo(leagueInfoList)


    }

    private fun handleLeagueInfo(leagueInfoList: ArrayList<LeagueData01>) {
        val sp = SPApp(requireContext())

        for (league in leagueInfoList) {
            list.add(
                LeagueModel(
                    leagueId = league.leagueId,
                    country = league.countryEn,
                    logo = league.leagueLogo,
                    leagueName = league.nameEn
                )
            )
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE)
                list.add(
                    LeagueModel(
                        leagueId = league.leagueId,
                        country = league.countryCn,
                        logo = league.leagueLogo,
                        leagueName = league.nameCn
                    )
                )


        }

        leagueListAdapter.submitList(list.distinct())
    }




        private fun handleIsLoadingState(loading: Boolean) {
            if (loading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        private fun handleFailure(message: String) {
            showToast(message)
            hideLoading()
        }


        private fun handleNetworkFailure(message: String) {
            showToast(message)
            hideLoading()
        }

        private fun handleException(message: String) {
            showToast(message)
            hideLoading()
        }


    }

