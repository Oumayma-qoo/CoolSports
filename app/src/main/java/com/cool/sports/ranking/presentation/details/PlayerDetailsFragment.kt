package com.cool.sports.ranking.presentation.details

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
import androidx.navigation.fragment.navArgs
import com.cool.sports.ranking.R
import com.cool.sports.ranking.common.constant.Constants
import com.cool.sports.ranking.common.sharedPreference.SPApp
import com.cool.sports.ranking.common.utils.CustomBindingAdapters
import com.cool.sports.ranking.common.utils.CustomBindingAdapters.loadImage
import com.cool.sports.ranking.databinding.FragmentPlayerDetailsBinding
import com.cool.sports.ranking.domain.model.team.BaseTeam
import com.cool.sports.ranking.domain.model.team.TeamPlayer
import com.cool.sports.ranking.presentation.base.BaseFragment
import com.cool.sports.ranking.presentation.teamStandings.TeamInfoScreenStanding
import com.cool.sports.ranking.presentation.teamStandings.TeamStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayerDetailsFragment : BaseFragment() {
    private val TAG = "PlayerDetailsFragment"

    private var _binding: FragmentPlayerDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TeamStandingsViewModel>()
    private lateinit var sp: SPApp
    private val args: PlayerDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = SPApp(requireContext())
        initObserver()
        binding.settingsIcon.setOnClickListener {
            val action = PlayerDetailsFragmentDirections.actionPlayerDetailFragmentToNavigationSettings()
            findNavController().navigate(action)
        }
        binding.closeImageView.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.closeImageView2.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }
        lifecycleScope.launch {
            viewModel.getTeamInfo(args.teamId)
        }
        setPlayerInfo(playerId = args.playerId, args.teamId)
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
//            is TeamInfoScreenStanding.Response -> handleTeamInfoResponse(state.teamInfo)
            is TeamInfoScreenStanding.NoInternetException -> handleNetworkFailure(state.message)
            is TeamInfoScreenStanding.GeneralException -> handleException(state.message)
            is TeamInfoScreenStanding.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

//    private fun handleTeamInfoResponse(response: BaseTeam) {
//        playerInfoList.addAll(response.teamPlayerData)
//        setPlayerInfo(playerId = args.playerId, args.teamId,playerInfoList)
//
//    }


    private fun setPlayerInfo(playerId: Int, teamId: Int) {
        viewModel.getPlayerInfoFromLocalDB(playerId, teamId)
        viewModel._playerInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                hideLoading()
                binding.constPlayerInfo.visibility= View.VISIBLE
                binding.teamName.text = it.nameEn
                binding.fullNameValue.text = it.nameEn
                binding.countryValue.text = it.countryEn
                binding.birthdayValue.text = it.birthday
                binding.heightValue.text = it.height
                binding.weightValue.text = it.weight
                binding.positionValue.text = it.positionEn
                binding.numberValue.text = it.number
                binding.contractEndingValue.text = it.endDateContract
                loadImage(binding.playerImg, it.photo)
                if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                    binding.teamName.text = it.nameCn
                    binding.fullNameValue.text = it.nameCn
                    binding.countryValue.text = it.countryCn
                    binding.positionValue.text = it.positionCn
                }
            } else
                viewModel._playerInfoLiveData.observe(viewLifecycleOwner) {

                    val player = it.find {
                        it.playerId == playerId
                    }
                    if (player != null) {
                        binding.constPlayerInfo.visibility = View.VISIBLE
                        binding.teamName.text = player!!.nameEn
                        binding.fullNameValue.text = player.nameEn
                        binding.countryValue.text = player.countryEn
                        binding.birthdayValue.text = player.birthday
                        binding.heightValue.text = player.height
                        binding.weightValue.text = player.weight
                        binding.positionValue.text = player.positionEn
                        binding.numberValue.text = player.number
                        binding.contractEndingValue.text = player.endDateContract
                        loadImage(binding.playerImg, player.photo)
                        if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                            binding.teamName.text = player.nameCn
                            binding.fullNameValue.text = player.nameCn
                            binding.countryValue.text = player.countryCn
                            binding.positionValue.text = player.positionCn
                        }
                    } else {
                        binding.playerImg.setImageResource(R.drawable.ic_no_player)
                        binding.playerImg.setPadding(20, 20, 20, 20)
                        binding.constPlayerInfo.visibility = View.GONE
                        binding.constNoPlayerInfo.visibility = View.VISIBLE

                    }

                }

        }
    }


    private fun handleIsLoadingState(loading: Boolean) {
        if (loading) {
            showLoading()
        }
        else {
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