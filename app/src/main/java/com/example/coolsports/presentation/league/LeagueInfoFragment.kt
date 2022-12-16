package com.example.coolsports.presentation.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueInfoFragment : BaseFragment() {

    val TAG: String = "LeagueInfoFragment"
    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()

        goToNext()

        val rules:  LeagueData04= arguments!!.getParcelable("rules")!!
        val leagueInfo:  LeagueData01 = arguments!!.getParcelable("LeagueInfo")!!

        Log.d(TAG, "rules========== $rules" )
        Log.d(TAG, "leagueInfo========== $leagueInfo" )
        try {
            val leagueStanding:   ArrayList<LeagueStandingsBase> = arguments!!.getParcelable("leagueStanding")!!

            Log.d(TAG, "leagueInfo========== $leagueStanding" )

        }catch (e:Exception)
        {

        }
        try {
            val leagueStandingGroup:  ArrayList<LeagueStandingsGroupBase> = arguments!!.getParcelable("leagueStandingGroup")!!
            Log.d(TAG, "leagueInfo========== $leagueStandingGroup" )

        }catch (e:Exception)
        {

        }






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