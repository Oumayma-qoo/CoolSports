package com.example.coolsports.presentation.details

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
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.CustomBindingAdapters.loadImage
import com.example.coolsports.databinding.FragmentLeagueDetailsBinding
import com.example.coolsports.domain.model.team.BaseTeam
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer
import com.example.coolsports.presentation.base.BaseFragment
import com.example.coolsports.presentation.teamStandings.TeamInfoScreenStanding
import com.example.coolsports.presentation.teamStandings.TeamStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LeagueDetailsFragment : BaseFragment() {
    val TAG: String = "LeagueDetailsFragment"

    private var _binding: FragmentLeagueDetailsBinding? = null
    private val binding get() = _binding!!
    val teamInfoList = ArrayList<TeamInfo>()
    val playerInfoList = ArrayList<TeamPlayer>()
    private val viewModel by viewModels<TeamStandingsViewModel>()
    private lateinit var navController: NavController
    private lateinit var sp: SPApp


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.getTeamInfo(88)

        }


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initObserver()

        sp = SPApp(requireContext())

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

    private fun handleTeamInfoResponse(response: BaseTeam) {
        teamInfoList.addAll(response.teamInfoData)
        playerInfoList.addAll(response.teamPlayerData)


        handleTeamInfo(teamInfoList)
        getMVP(88)
        topScorers(650)

    }


    private fun handleTeamInfo(teamInfo: ArrayList<TeamInfo>) {

        viewModel.getTeamInfoFromLocalDB(88)
        viewModel._teamInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.teamName.text = it.nameEn
                binding.fullNameValue.text = it.nameEn
                binding.coachValue.text = it.coachEn
                binding.capacityValue.text = it.capacity
                binding.foundingDateValue.text = it.foundingDate
                binding.websiteValue.text = it.website
                binding.areaValue.text = it.areaEn
                loadImage(binding.teamImg, it.logo)
                if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                    binding.teamName.text = it.nameCn
                    binding.fullNameValue.text = it.nameCn
                    binding.coachValue.text = it.coachCn
                    binding.areaValue.text = it.areaCn

                }

            } else {
                for (team in teamInfo) {
                    binding.teamName.text = team.nameEn
                    binding.fullNameValue.text = team.nameEn
                    binding.coachValue.text = team.coachEn
                    binding.capacityValue.text = team.capacity
                    binding.foundingDateValue.text = team.foundingDate
                    binding.websiteValue.text = team.website
                    binding.areaValue.text = team.areaEn
                    loadImage(binding.teamImg, team.logo)
                    if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                        binding.teamName.text = team.nameCn
                        binding.fullNameValue.text = team.nameCn
                        binding.coachValue.text = team.coachCn
                        binding.areaValue.text = team.areaCn

                    }
                }


            }
        }


    }

    private fun getMVP(teamId: Int) {
        viewModel.getMVPFromLocalDB(teamId)
        viewModel._MVP.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.playerTitle.text = it.nameEn
                binding.playerValue.text = it.value
                if (sp.language == Constants.SharedPreferenceKeys.CHINESE)
                    binding.playerTitle.text = it.nameCn

            }
        }

    }


    private fun topScorers(teamId: Int) {
        viewModel.getPlayerListOrderByGoals(teamId)
        viewModel._playersList.observe(viewLifecycleOwner) {

            if (it != null) {
                binding.player1Title.text = it[0].playerNameEn
                binding.player1Value.text = it[0].goals.toString()

                binding.player2Title.text = it[1].playerNameEn
                binding.player2Value.text = it[1].goals.toString()

                binding.player3Title.text = it[2].playerNameEn
                binding.player3Value.text = it[2].goals.toString()

                if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                    binding.player1Title.text = it[0].playerNameChs
                    binding.player2Title.text = it[1].playerNameChs
                    binding.player3Title.text = it[2].playerNameChs
                }
            }
        }
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