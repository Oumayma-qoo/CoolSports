package com.example.coolsports.presentation.teamStandings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolsports.databinding.FragmentTeamStandingsBinding
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.domain.model.leagueStandings.TotalStanding
import com.example.coolsports.presentation.league.LeagueInfoFragmentDirections
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo
import com.example.coolsports.presentation.league.LeagueViewModel
import com.example.coolsports.presentation.playerStandings.PlayerStandingsViewModel
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.example.coolsports.presentation.league.LeagueInfoFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamStandingsFragment(val rules: LeagueData04, var leagueStanding: List<LeagueStandingsBase> ,var leagueStanding2: List<LeagueStandingsGroupBase>,val leagueId: Int,  val viewModel: LeagueViewModel) : Fragment() {

    val TAG: String = "TeamStandingsFragment"
    private var _binding: FragmentTeamStandingsBinding? = null
    private val binding get() = _binding!!
    var bundle = Bundle()
    var leagueList = listOf<Any>()
    private lateinit var teamStandingAdapter: TeamStandingAdapter
    private var mappedData = mutableListOf<TotalStandingWithTeamInfo>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rulesValue.text = rules.ruleEn

        if (leagueStanding.isEmpty())
            return
        if (leagueStanding[0].totalStandings.isNotEmpty()) {
            binding.teamStandingContainer.visibility = View.VISIBLE
            binding.topThree.visibility = View.VISIBLE
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
                if (team != null) {
                    item.nameEn = team.nameEn
                }
                mappedData.add(item)
            }

            binding.firstTeamName.text = teamInfo.find {
                it.teamId == totalStanding[0].teamId
            }?.nameEn
            binding.secondTeamName.text = teamInfo.find {
                it.teamId == totalStanding[1].teamId
            }?.nameEn

            binding.thirdTeamName.text = teamInfo.find {
                it.teamId == totalStanding[2].teamId
            }?.nameEn


            binding.teamStandingContainer.setOnClickListener {
                val action =
                    LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                        totalStanding[0].teamId!!

                    )
                findNavController().navigate(action)
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
            binding.teamRecyclerView.adapter= teamStandingAdapter


        } else if (leagueStanding2[0].list.isNotEmpty()) {
            binding.topThree.visibility = View.GONE
            binding.teamStandingContainer.visibility = View.GONE
            binding.teamStandingGroupContainer.visibility = View.VISIBLE
            binding.teamRecyclerGroupView.adapter =
                TeamStandingGroupAdapter(requireContext(), object : OnGroupItemsClickListener {
                    override fun onClickListener(item: ScoreItem) {
                        val action =
                            LeagueInfoFragmentDirections.actionLeagueInfoFragmentToLeagueDetailFragment(
                                item.teamId.toInt()
                            )
                        findNavController().navigate(action)


                }
            },leagueStanding2[0].list[0].score[0].groupScore)
        }

        viewModel.queryLiveData.observe(viewLifecycleOwner) {
            teamStandingAdapter.filter.filter(it)
        }
    }


    fun goToNext() {
        binding.secondNum.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.TeamStandingsFragment)
                findNavController().navigate(R.id.action_teamStandingFragment_to_teamInfoFragment)
        }


    }


}