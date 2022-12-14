package com.cool.sports.ranking.presentation.playerStandings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cool.sports.ranking.common.constant.Constants
import com.cool.sports.ranking.common.sharedPreference.SPApp
import com.cool.sports.ranking.databinding.FragmentPlayerStandingBinding
import com.cool.sports.ranking.domain.model.player.BasePlayerStanding
import com.cool.sports.ranking.domain.model.player.Player
import com.cool.sports.ranking.presentation.base.BaseFragment
import com.cool.sports.ranking.presentation.league.LeagueInfoFragmentDirections
import com.cool.sports.ranking.presentation.league.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class PlayerStandingsFragment(val leagueId: Int,  private val viewModelLeague: LeagueViewModel) : BaseFragment() {
    val TAG: String = "HomeFragment"
    private var _binding: FragmentPlayerStandingBinding? = null
    private val binding get() = _binding!!
    var playerList = ArrayList<Player>()
    private val viewModel by viewModels<PlayerStandingsViewModel>()
    var playerId:Int by Delegates.notNull<Int>()
    private lateinit var playerAdapter: PlayerStandingAdapter
    private lateinit var sp: SPApp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerStandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPlayerStanding(leagueId, "0")

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
        sp = SPApp(requireContext())



    }



    private fun initObserver() {
        lifecycleScope.launch{
            viewModel.mState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{
                    handleState(it)
                }
        }
    }

    private fun handleState(state: PlayerStandingScreenState) {
        when (state) {
            is PlayerStandingScreenState.IsLoading -> handleIsLoadingState(state.isLoading)
            is PlayerStandingScreenState.Response -> handlePlayerStandingsResponse(state.playerStanding)
            is PlayerStandingScreenState.NoInternetException -> handleNetworkFailure(state.message)
            is PlayerStandingScreenState.GeneralException -> handleException(state.message)
            is PlayerStandingScreenState.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private fun handlePlayerStandingsResponse(response: BasePlayerStanding) {
        playerList.addAll(response.list)

        binding.firstTeamName.text = playerList[0].playerNameEn
        binding.secondTeamName.text = playerList[1].playerNameEn
        binding.thirdTeamName.text = playerList[2].playerNameEn
        if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
            binding.firstTeamName.text = playerList[0].playerNameChs
            binding.secondTeamName.text = playerList[1].playerNameChs
            binding.thirdTeamName.text = playerList[2].playerNameChs
        }
        binding.firstContainer.setOnClickListener {
            val action = LeagueInfoFragmentDirections.actionLeagueFragmentInfoToPlayerDetailFragment(
                playerList[0].playerId!!, playerList[0].teamID!!,playerList[0]
            )
            findNavController().navigate(action)
        }
        binding.secondContainer.setOnClickListener {
            val action = LeagueInfoFragmentDirections.actionLeagueFragmentInfoToPlayerDetailFragment(
                playerList[1].playerId!!, playerList[1].teamID!!,playerList[1]
            )
            findNavController().navigate(action)
        }
       binding.thirdContainer.setOnClickListener {
            val action = LeagueInfoFragmentDirections.actionLeagueFragmentInfoToPlayerDetailFragment(
                playerList[2].playerId!!, playerList[2].teamID!!,playerList[2]
            )
            findNavController().navigate(action)
        }

        binding.playerRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.playerRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        playerAdapter = PlayerStandingAdapter(requireContext(), object : OnPlayerClickListener {
            override fun onClickListener(player: Player) {
                val action = LeagueInfoFragmentDirections.actionLeagueFragmentInfoToPlayerDetailFragment(
                    player.playerId!!, player.teamID!!,player
                )
                findNavController().navigate(action)

            }

        },playerList)
        binding.playerRecyclerView.adapter= playerAdapter


        viewModelLeague.queryLiveData.observe(viewLifecycleOwner) {
            playerAdapter.filter.filter(it)
        }
        hideLoading()
    }


    private fun handleIsLoadingState(loading: Boolean) {
        if (loading) {
            showLoading()
            Log.d(TAG, "show loader....")
        } else {
            hideLoading()
            Log.d(TAG, "..... stop loader")
        }
    }

    private fun handleFailure(message: String) {
        Log.d(TAG, "failure    $message")
        showToast(message)
        hideLoading()
    }


    private fun handleNetworkFailure(message: String) {
        Log.d(TAG, "network    $message")
        showToast(message)
        hideLoading()
    }

    private fun handleException(message: String) {
        Log.d(TAG, "exception    $message")
        showToast(message)
        hideLoading()
    }

}