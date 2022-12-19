package com.example.coolsports.presentation.teamStandings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentLeagueBinding
import com.example.coolsports.databinding.FragmentTeamStandingsBinding
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.league.LeagueEntity
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.GroupScore
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.domain.model.leagueStandings.TotalStanding
import com.example.coolsports.presentation.league.LeagueViewModel
import com.example.coolsports.presentation.playerStandings.PlayerStandingsViewModel
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class TeamStandingsFragment(val rules: LeagueData04,var leagueStanding: List<Any>,val leagueId: Int) : Fragment() {

    val TAG: String = "TeamStandingsFragment"
    private var _binding: FragmentTeamStandingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var bundle = Bundle()
    var leagueList = listOf<Any>()
    private val viewModel by viewModels<TeamStandingsViewModel>()
    private lateinit var teamStandingAdapter: TeamStandingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // navController = view.findNavController()
//        leagueList= arguments!!.getParcelableArrayList("LeagueStandingsList")!!
//        Log.d(TAG, leagueList.toString())
//        goToNext()


        binding.rulesValue.text = rules.ruleEn
        Log.d(TAG, "leagueStanding====== $leagueStanding")


      //  ((leagueStanding as ArrayList<*>)[0] as LinkedTreeMap<*,*>).containsKey("list")

        if (leagueStanding.filterIsInstance<List<LeagueStandingsBase>>().isNotEmpty()){
            binding.teamStandingContainer.visibility = View.VISIBLE
            binding.topThree.visibility = View.VISIBLE
           // binding.firstTeamName.text =
            val totalStanding = leagueStanding.filterIsInstance<LeagueStandingsBase>()[0].totalStandings
            val teamInfo = leagueStanding.filterIsInstance<LeagueStandingsBase>()[0].leagueInfo
            binding.teamRecyclerView.adapter= TeamStandingAdapter(requireContext(), object : OnTeamClickListener {
                 override fun onClickListener(totalStanding: TotalStanding) {

                 }
             },teamInfo,totalStanding)


        }else if (((leagueStanding as ArrayList<*>)[0] as LinkedTreeMap<*,*>).containsKey("list")){
            binding.topThree.visibility = View.GONE
            binding.teamStandingContainer.visibility = View.GONE
            binding.teamStandingGroupContainer.visibility = View.VISIBLE
            val groupScore = ((((((((leagueStanding as ArrayList<*>)[0] as LinkedTreeMap<*,*>)["list"] as ArrayList<*> )[0] as LinkedTreeMap<*,*>)["score"] as ArrayList<*>)[0] as LinkedTreeMap<*,*>)["groupScore"]) as ArrayList<*>)
//            binding.teamRecyclerGroupView.adapter= TeamStandingGroupAdapter(requireContext(), object : OnGroupItemsClickListener {
//                override fun onClickListener(item: ScoreItem) {
//
//                }
//            },groupScore)
        }


    }


    fun goToNext() {
        binding.secondNum.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.TeamStandingsFragment)
                findNavController().navigate(R.id.action_teamStandingFragment_to_teamInfoFragment)
        }


    }

}