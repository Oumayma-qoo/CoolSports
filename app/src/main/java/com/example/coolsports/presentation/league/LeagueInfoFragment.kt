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
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentLeagueBinding
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueEntity
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.presentation.base.BaseFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LeagueInfoFragment : BaseFragment() {

    val TAG: String = "LeagueFragment"
    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val viewModel by viewModels<LeagueViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()

        goToNext()
    }


    fun goToNext() {
//        binding.goTeamBtn.setOnClickListener {
//            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
//                navController.navigate(R.id.action_leagueFragment_to_teamStandingsFragment,bundle)
//        }
//        binding.goPlayerBtn.setOnClickListener {
//            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
//                navController.navigate(R.id.action_leagueFragment_to_playerStandingsFragment)
//        }



    }


}