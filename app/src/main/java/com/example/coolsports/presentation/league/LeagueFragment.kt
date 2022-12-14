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
class LeagueFragment : BaseFragment() {

    val TAG: String = "LeagueFragment"
    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var leagueInfoList = ArrayList<LeagueData01>()
    var leagueInfo = LeagueEntity()
    private val viewModel by viewModels<LeagueViewModel>()
    var bundle= Bundle()

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
        initObserver()

        lifecycleScope.launch {
            viewModel.getLeagueInfo(1329, " ", 22234)
        }
        goToNext()
    }


    fun goToNext() {
        binding.goTeamBtn.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                navController.navigate(R.id.action_leagueFragment_to_teamStandingsFragment,bundle)
        }
        binding.goPlayerBtn.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                navController.navigate(R.id.action_leagueFragment_to_playerStandingsFragment)
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
        handleLeagueInfoData(leagueInfoList)
        try {
            bundle.putParcelableArrayList("LeagueStandingsList",response.leagueStanding as java.util.ArrayList<out Parcelable>)
        }catch (e:Exception){
            println(e.message)
        }

        testCasting(response)


    }


    private fun testCasting(leagueInfoBase: BaseLeagueInfo): BaseLeagueInfo {
        try {
            val obj=leagueInfoBase.leagueStanding[0]
            val jsonObj= Gson().toJson(obj)
            val groupObj= Gson().fromJson(jsonObj,LeagueStandingsGroupBase::class.java)
            try {
                println(groupObj.list[0].leagueId)

                leagueInfoBase.leagueStanding= listOf<LeagueStandingsGroupBase>(groupObj)

            }catch (e:Exception){

                val groupObjOriginal= Gson().fromJson(jsonObj,LeagueStandingsBase::class.java)
                leagueInfoBase.leagueStanding= listOf<LeagueStandingsBase>(groupObjOriginal)
                println(leagueInfoBase)
            }
            when (leagueInfoBase.leagueStanding[0]){
                is LeagueStandingsGroupBase->{
                    println("Groups Base")
                }
                is LeagueStandingsBase->{
                    println("Standard Base")
                }
                else->{
                    println("Bad News!")
                }
            }
        }catch (e:Exception){

        }
        return leagueInfoBase
    }







    private fun handleLeagueInfoData(leagueInfoList: java.util.ArrayList<LeagueData01>) {
        for (league in leagueInfoList) {
            if (league.leagueId == 1329) {
                if (league.nameEn.isNotEmpty() && league.nameEnShort.isNotEmpty() && league.countryEn.isNotEmpty() && league.currSeason.isNotEmpty()) {
                    leagueInfo = (LeagueEntity(
                        league.nameEn,
                        league.nameEnShort,
                        league.leagueLogo,
                        league.type,
                        league.countryEn,
                        league.currSeason
                    ))

                }
            }


        }

    }


    private fun handleIsLoadingState(loading: Boolean) {
        if (loading) {
            showLoading()
            Log.d(TAG, "show loader....")
        } else {
            hideLoading()
            Log.d(TAG, "..... stop loader")
        }
    }

    private fun handleFailure(message: String) {
        Log.d(TAG, "failure    $message")
        showToast(message)
        hideLoading()
    }


    private fun handleNetworkFailure(message: String) {
        Log.d(TAG, "network    $message")
        showToast(message)
        hideLoading()
    }

    private fun handleException(message: String) {
        Log.d(TAG, "exception    $message")
        showToast(message)
        hideLoading()
    }


}