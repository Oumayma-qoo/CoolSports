package com.example.coolsports.presentation.league

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
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.league.LeagueModel
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.presentation.base.BaseFragment
import com.example.coolsports.presentation.teamStandings.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class LeagueInfoFragment : BaseFragment() {

    val TAG: String = "LeagueInfoFragment"
    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()
    var leagueInfoList = ArrayList<LeagueData01>()
    var listLeague = mutableListOf<LeagueModel>()
    var list = mutableListOf<LeagueModel>()
    var listRules = ArrayList<LeagueData04>()
    var leagueStandingGroup = ArrayList<LeagueStandingsGroupBase>()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    var leagueRules = ArrayList<LeagueData04>()
    private var  leagueId by Delegates.notNull<Int>()

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

        initObserver()

        leagueId = arguments!!.getInt("leagueId")
        Log.d(TAG, leagueId.toString())



        lifecycleScope.launch {
            viewModel.getLeagueInfo(leagueId, " ", 0)

        }


    }



    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED

        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun handleState(state: LeagueStateScreen) {
        when (state) {
            is LeagueStateScreen.IsLoading -> handleIsLoadingState(state.isLoading)
            is LeagueStateScreen.Response -> handleLeagueResponse(state.leagueInfo)
            is LeagueStateScreen.NoInternetException -> handleNetworkFailure(state.message)
            is LeagueStateScreen.GeneralException -> handleException(state.message)
            is LeagueStateScreen.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private fun handleLeagueResponse(response: BaseLeagueInfo) {
        leagueInfoList.addAll(response.leagueData01)
        leagueRules.addAll(response.leagueData04)



        Log.d(TAG, "leagueStanding====== ${response.leagueStanding}")

        if (leagueInfoList.isNotEmpty()) {
            val leagueInfo = leagueInfoList.find {
                it.leagueId == leagueId
            }

            binding.screenTitle.text = leagueInfo?.nameEn
            binding.fullNameValue.text = leagueInfo?.nameEn
            binding.shortNameValue.text = leagueInfo?.nameEnShort
            binding.typeValue.text = leagueInfo?.type
            binding.countryValue.text = leagueInfo?.countryEn
            binding.currentSeasonValue.text = leagueInfo?.currSeason

            Glide.with(requireContext())
                .load(leagueInfo?.leagueLogo)
                .into(binding.leagueImageView)
        }

        if (leagueRules.isNotEmpty()) {
            val rules = leagueRules.find {
                it.leagueId == leagueId
            }

            viewPagerAdapter = rules?.let { ViewPagerAdapter(this, it, response.leagueStanding, leagueId) }!!
            binding.viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                object : TabLayoutMediator.TabConfigurationStrategy {
                    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                        tab.text = resources.getStringArray(R.array.tab_names)[position]

                        binding.viewPager.setCurrentItem(tab.position, true)
                    }
                }).attach()
            binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
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






