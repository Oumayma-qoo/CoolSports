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
import androidx.navigation.fragment.navArgs
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
    private val teamInfoList = ArrayList<TeamInfo>()
    private val playerInfoList = ArrayList<TeamPlayer>()
    private val viewModel by viewModels<TeamStandingsViewModel>()
    private lateinit var navController: NavController
    private lateinit var sp: SPApp
    private val args:LeagueDetailsFragmentArgs by navArgs()

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
            viewModel.getTeamInfo(args.teamId)

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
        getMVP(args.teamId, playerInfoList)
        topScorers(args.teamId)


    }


    private fun handleTeamInfo(teamInfo: ArrayList<TeamInfo>) {

        viewModel.getTeamInfoFromLocalDB(args.teamId)
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
                when (sp.language) {
                    Constants.SharedPreferenceKeys.CHINESE -> {
                        binding.teamName.text = it.nameCn
                        binding.fullNameValue.text = it.nameCn
                        binding.coachValue.text = it.coachCn
                        binding.areaValue.text = it.areaCn

                    }
                    Constants.SharedPreferenceKeys.VIETNAMESE -> {
                        binding.teamName.text = it.nameVi
                        binding.fullNameValue.text = it.nameVi
                        binding.coachValue.text = it.coachVi
                        binding.areaValue.text = it.areaVi

                    }
                    Constants.SharedPreferenceKeys.INDONESIAN -> {
                        binding.teamName.text = it.nameId
                        binding.fullNameValue.text = it.nameId
                        binding.coachValue.text = it.coachId
                        binding.areaValue.text = it.areaId

                    }
                    Constants.SharedPreferenceKeys.TAI -> {
                        binding.teamName.text = it.nameTh
                        binding.fullNameValue.text = it.nameTh
                        binding.coachValue.text = it.coachTh
                        binding.areaValue.text = it.areaTh

                    }
                    Constants.SharedPreferenceKeys.ENGLISH -> {
                        binding.teamName.text = it.nameEn
                        binding.fullNameValue.text = it.nameEn
                        binding.coachValue.text = it.coachEn
                        binding.areaValue.text = it.areaEn

                    }
                    else -> binding.teamName.text = it.nameEn

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
                    when (sp.language) {
                        Constants.SharedPreferenceKeys.CHINESE -> {
                            binding.teamName.text = team.nameCn
                            binding.fullNameValue.text = team.nameCn
                            binding.coachValue.text = team.coachCn
                            binding.areaValue.text = team.areaCn

                        }
                        Constants.SharedPreferenceKeys.VIETNAMESE -> {
                            binding.teamName.text = team.nameVi
                            binding.fullNameValue.text = team.nameVi
                            binding.coachValue.text = team.coachVi
                            binding.areaValue.text = team.areaVi

                        }
                        Constants.SharedPreferenceKeys.INDONESIAN -> {
                            binding.teamName.text = team.nameId
                            binding.fullNameValue.text = team.nameId
                            binding.coachValue.text = team.coachId
                            binding.areaValue.text = team.areaId

                        }
                        Constants.SharedPreferenceKeys.TAI -> {
                            binding.teamName.text = team.nameTh
                            binding.fullNameValue.text = team.nameTh
                            binding.coachValue.text = team.coachTh
                            binding.areaValue.text = team.areaTh

                        }
                        Constants.SharedPreferenceKeys.ENGLISH -> {
                            binding.teamName.text = team.nameEn
                            binding.fullNameValue.text = team.nameEn
                            binding.coachValue.text = team.coachEn
                            binding.areaValue.text = team.areaEn

                        }
                        else -> binding.teamName.text = team.nameEn

                    }

                }


            }
        }


    }

    private fun getMVP(teamId: Int, playerInfo: ArrayList<TeamPlayer>) {
        var max = playerInfo[0].value.toString()

        viewModel.getMVPFromLocalDB(teamId)
        viewModel._MVP.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.playerTitle.text = it.nameEn
                binding.playerValue.text = it.value
                when (sp.language) {
                    Constants.SharedPreferenceKeys.ENGLISH -> binding.playerTitle.text = it.nameEn
                    Constants.SharedPreferenceKeys.CHINESE -> binding.playerTitle.text = it.nameCn
                }

            } else
                for (player in playerInfo) {
                    if (max < player.value.toString())
                        max = player.value.toString()


            val player = playerInfo.find { it ->
                it.value == max
            }
            binding.playerTitle.text = player!!.nameEn
            binding.playerValue.text = player.value
                    when (sp.language) {
                        Constants.SharedPreferenceKeys.ENGLISH -> binding.playerTitle.text = player.nameEn
                        Constants.SharedPreferenceKeys.CHINESE -> binding.playerTitle.text = player.nameCn
                    }
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

                    when (sp.language) {
                        Constants.SharedPreferenceKeys.ENGLISH -> {
                            binding.player1Title.text = it[0].playerNameEn
                            binding.player2Title.text = it[1].playerNameEn
                            binding.player3Title.text = it[2].playerNameEn
                        }
                        Constants.SharedPreferenceKeys.CHINESE -> {
                            binding.player1Title.text = it[0].playerNameChs
                            binding.player2Title.text = it[1].playerNameChs
                            binding.player3Title.text = it[2].playerNameChs
                        }

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