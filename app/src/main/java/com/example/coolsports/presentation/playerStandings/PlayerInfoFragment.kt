package com.example.coolsports.presentation.playerStandings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentPlayerInfoBinding
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer
import com.example.coolsports.presentation.base.BaseFragment
import com.example.coolsports.presentation.teamStandings.TeamStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PlayerInfoFragment : BaseFragment() {

    val TAG: String = "PlayerInfoFragment"
    private var _binding: FragmentPlayerInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<PlayerStandingsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()

        viewModel.getPlayerInfoFromLocalDB(181304)
        viewModel._playerInfo.observe(viewLifecycleOwner){
            Log.d(TAG, "PlayerInfo:  " +it)
        }

    }




}