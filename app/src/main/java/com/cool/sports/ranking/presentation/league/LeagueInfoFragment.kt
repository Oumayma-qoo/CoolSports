package com.cool.sports.ranking.presentation.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cool.sports.ranking.R
import com.cool.sports.ranking.common.constant.Constants
import com.cool.sports.ranking.common.sharedPreference.SPApp
import com.cool.sports.ranking.databinding.FragmentTeamInfoBinding
import com.cool.sports.ranking.domain.model.league.BaseLeagueInfo
import com.cool.sports.ranking.domain.model.league.LeagueData01
import com.cool.sports.ranking.domain.model.league.LeagueData04
import com.cool.sports.ranking.domain.model.league.LeagueModel
import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.cool.sports.ranking.presentation.base.BaseFragment
import com.cool.sports.ranking.presentation.teamStandings.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class LeagueInfoFragment : BaseFragment(), SearchView.OnQueryTextListener  {

    val TAG: String = "LeagueInfoFragment"
    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()
    var leagueInfoList = ArrayList<LeagueData01>()
    var list = mutableListOf<LeagueModel>()
    var leagueStandingGroup = ArrayList<LeagueStandingsGroupBase>()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    var leagueRules = ArrayList<LeagueData04>()
    private var  leagueId by Delegates.notNull<Int>()
    private lateinit var sp: SPApp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagueId = arguments!!.getInt("leagueId")
        lifecycleScope.launch {
            viewModel.getLeagueInfo(leagueId, " ", 0)

        }

        lifecycleScope.launch {
            viewModel.getPlayerStanding(leagueId, "0")

        }
        lifecycleScope.launch {
            viewModel.getTeamInfo(leagueId)

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sp = SPApp(requireContext())

        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }
        navController = view.findNavController()

        initObserver()
        binding.searchView.setOnQueryTextListener(this)





        goToSettings()
    }



    private fun initObserver() {
        lifecycleScope.launch{
            viewModel.mState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleState(it)
                }
        }
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
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                binding.screenTitle.text = leagueInfo?.nameCn
                binding.fullNameValue.text = leagueInfo?.nameCn
                binding.shortNameValue.text = leagueInfo?.nameCnShort
                binding.countryValue.text = leagueInfo?.countryCn
            }


            Glide.with(requireContext())
                .load(leagueInfo?.leagueLogo)
                .into(binding.leagueImageView)
        }

        if (leagueRules.isNotEmpty()) {
            val rules = leagueRules.find {
                it.leagueId == leagueId
            }


            viewPagerAdapter = rules?.let { ViewPagerAdapter(this, it, response.leagueStanding, response.leagueStanding2, leagueId, viewModel) }!!
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.queryLiveData.postValue(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.queryLiveData.postValue(newText)
        return false
    }

    private fun goToSettings(){
        binding.settingsIcon.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragmentInfo)
                navController.navigate(
                    R.id.action_LeagueInfoFragment_to_navigation_settings)
        }
    }

}






