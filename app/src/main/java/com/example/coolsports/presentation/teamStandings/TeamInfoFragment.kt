package com.example.coolsports.presentation.teamStandings

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
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.domain.model.team.BaseTeam
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TeamInfoFragment : BaseFragment() {

    val TAG: String = "TeamInfoFragment"
    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    val teamInfoList = ArrayList<TeamInfo>()
    val playerInfoList = ArrayList<TeamPlayer>()

    private val viewModel by viewModels<TeamStandingsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initObserver()


        lifecycleScope.launch {
            viewModel.getTeamInfo(20326)

        }

    }

    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED

        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun handleState(state: TeamInfoScreenStanding) {
        when (state) {
            is TeamInfoScreenStanding.IsLoading -> handleIsLoadingState(state.isLoading)
            is TeamInfoScreenStanding.Response -> handleTeamInfoResponse(state.teamInfo)
            is TeamInfoScreenStanding.NoInternetException -> handleNetworkFailure(state.message)
            is TeamInfoScreenStanding.GeneralException -> handleException(state.message)
            is TeamInfoScreenStanding.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private  fun handleTeamInfoResponse(response: BaseTeam) {
        teamInfoList.addAll(response.teamInfoData)
        playerInfoList.addAll(response.teamPlayerData)
        Log.d(TAG, " response success teamInfoList " + teamInfoList)
        Log.d(TAG, " response success playerInfoList " + playerInfoList)
        viewModel.getTeamInfoFromLocalDB(20326)
        viewModel._teamInfo.observe(viewLifecycleOwner){
            Log.d(TAG, "TeamInfo:  " +it)
        }

        viewModel.getPlayerInfoFromLocalDB(181304)
        viewModel._playerInfo.observe(viewLifecycleOwner){
            Log.d(TAG, "PlayerInfo:  " +it)
        }

        viewModel.getMVPFromLocalDB()
        viewModel._MVP.observe(viewLifecycleOwner){
            Log.d(TAG, "MVP:  " +it)
        }

        viewModel.getPlayerListOrderByGoals(26)
        viewModel._playersList.observe(viewLifecycleOwner){
            Log.d(TAG, "Players list orede by goals:  " +it)
        }

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