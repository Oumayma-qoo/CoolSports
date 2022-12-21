package com.example.coolsports.presentation.teamStandings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.databinding.FragmentTeamStandingsBinding
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo
import com.example.coolsports.presentation.league.LeagueInfoFragmentDirections
import com.example.coolsports.presentation.league.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamStandingsFragment(val rules: LeagueData04, var leagueStanding: List<LeagueStandingsBase> ,var leagueStanding2: List<LeagueStandingsGroupBase>,val leagueId: Int,  val viewModel: LeagueViewModel) : Fragment() {

    val TAG: String = "TeamStandingsFragment"
    private var _binding: FragmentTeamStandingsBinding? = null
    private val binding get() = _binding!!
    var bundle = Bundle()
    var leagueList = listOf<Any>()
    private lateinit var teamStandingAdapter: TeamStandingAdapter
    private lateinit var teamStandingGroupAdapter: TeamStandingGroupAdapter
    private var mappedData = mutableListOf<TotalStandingWithTeamInfo>()
    private lateinit var sp: SPApp


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sp = SPApp(requireContext())
        binding.rulesValue.text = rules.ruleEn

        if (leagueStanding.isEmpty())
            return
        if (leagueStanding[0].totalStandings.isNotEmpty()) {
            binding.teamStandingContainer.visibility = View.VISIBLE
            binding.topThree.visibility = View.VISIBLE
            binding.teamStandingGroupContainer.visibility = View.GONE
            val totalStanding = leagueStanding[0].totalStandings
            val teamInfo = leagueStanding[0].teamInfo
            mappedData.clear()
            totalStanding.forEach{

                val item = TotalStandingWithTeamInfo(
                    rank = it.rank,
                    totalCount = it.totalCount,
                    winCount = it.winCount,
                    drawCount = it.drawCount,
                    loseCount = it.loseCount,
                    goalDifference = it.goalDifference,
                    integral = it.integral
                )
               val team =  teamInfo.find { teamInfo->
                    teamInfo.teamId == it.teamId
                }
                item.teamId = team?.teamId
                if (team != null) {
                    item.nameEn = team.nameEn
                    item.nameCn = team.nameCn
                }
                mappedData.add(item)
            }

            val team1 = teamInfo.find {
                it.teamId == totalStanding[0].teamId
            }

            val team2 = teamInfo.find {
                it.teamId == totalStanding[1].teamId
            }

            val team3 = teamInfo.find {
                it.teamId == totalStanding[2].teamId
            }
            binding.firstTeamName.text = team1?.nameEn
            binding.secondTeamName.text = team2?.nameEn
            binding.thirdTeamName.text = team3?.nameEn
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                binding.firstTeamName.text = team1?.nameCn
                binding.secondTeamName.text = team2?.nameCn
                binding.thirdTeamName.text = team3?.nameCn
            }

            binding.firstContainer.setOnClickListener {
                val action =
                    LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                        totalStanding[0].teamId!!

                    )
                findNavController().navigate(action)
            }
            binding.secondContainer.setOnClickListener {
                val action =
                    LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                        totalStanding[0].teamId!!

                    )
                findNavController().navigate(action)
            }
            binding.thirdContainer.setOnClickListener {
                val action =
                    LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                        totalStanding[0].teamId!!

                    )
                findNavController().navigate(action)
            }



            binding.teamRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            teamStandingAdapter = TeamStandingAdapter(requireContext(), object : OnTeamClickListener {
                override fun onClickListener(totalStanding: TotalStandingWithTeamInfo) {

                    val action =
                        LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                            totalStanding.teamId!!
                        )
                    findNavController().navigate(action)

                }
            },mappedData)
            binding.teamRecyclerView.adapter = teamStandingAdapter

            viewModel.queryLiveData.observe(viewLifecycleOwner) {
                teamStandingAdapter.filter.filter(it)
            }

        } else if (leagueStanding2[0].list.isNotEmpty()) {
            binding.topThree.visibility = View.GONE
            binding.teamStandingContainer.visibility = View.GONE
            binding.teamStandingGroupContainer.visibility = View.VISIBLE

            teamStandingGroupAdapter =  TeamStandingGroupAdapter(requireContext(), object : OnGroupItemsClickListener {
                override fun onClickListener(item: ScoreItem) {
                    val action =
                        LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                            item.teamId.toInt()
                        )
                    findNavController().navigate(action)


                }
            },leagueStanding2[0].list[0].score[0].groupScore)

            binding.teamRecyclerGroupView.adapter =teamStandingGroupAdapter


            viewModel.queryLiveData.observe(viewLifecycleOwner) {
                teamStandingGroupAdapter.filter.filter(it)
            }

        }


    }





}