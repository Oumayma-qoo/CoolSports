package com.example.coolsports.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.CustomBindingAdapters
import com.example.coolsports.databinding.FragmentPlayerDetailsBinding
import com.example.coolsports.presentation.base.BaseFragment
import com.example.coolsports.presentation.playerStandings.PlayerStandingsViewModel
import com.example.coolsports.presentation.teamStandings.TeamStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class PlayerDetailsFragment : BaseFragment() {

    private var _binding: FragmentPlayerDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PlayerStandingsViewModel>()
    private lateinit var sp: SPApp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlayerInfo(101944)
        sp = SPApp(requireContext())

    }


    fun setPlayerInfo(playerId: Int) {

        viewModel.getPlayerInfoFromLocalDB(playerId)
        viewModel._playerInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.teamName.text = it.nameEn
                binding.fullNameValue.text = it.nameEn
                binding.countryValue.text = it.countryEn
                binding.birthdayValue.text = it.birthday
                binding.heightValue.text = it.height
                binding.weightValue.text = it.weight
                binding.positionValue.text = it.positionEn
                binding.numberValue.text = it.number
                binding.contractEndingValue.text = it.endDateContract
                CustomBindingAdapters.loadImage(binding.playerImg, it.photo)
                if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                    binding.teamName.text = it.nameCn
                    binding.fullNameValue.text = it.nameCn
                    binding.countryValue.text = it.countryCn
                    binding.positionValue.text = it.positionCn

                }

            }

        }

    }
}