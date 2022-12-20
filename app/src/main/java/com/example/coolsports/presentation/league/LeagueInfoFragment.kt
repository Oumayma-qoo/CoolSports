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
import androidx.navigation.fragment.findNavController
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.LeagueData01
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.league.LeagueModel
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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

        val leagueId = arguments!!.getInt("leagueId")
        Log.d(TAG, leagueId.toString())



        lifecycleScope.launch {
            viewModel.getLeagueInfo(leagueId, " ", 0)

        }


        binding.screenTitle  .setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragmentInfo)
                navController.navigate(
                    R.id.action_leagueInfoFragment_to_leagueDetailFragment)}

        binding.settingsIcon.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragmentInfo)
                navController.navigate(
                    R.id.action_leagueInfoFragment_to_playerStandingsFragment)}




    }



//        private fun testCasting(leagueInfoBase: BaseLeagueInfo): BaseLeagueInfo {
//            val obj = leagueInfoBase.leagueStanding
//            val jsonObj = Gson().toJson(obj)
//            val gsonBuilder = GsonBuilder()
//            val gson: Gson = gsonBuilder.create()
//
//            val groupObj: Array<LeagueStandingsGroupBase> =
//                gson.fromJson(jsonObj, Array<LeagueStandingsGroupBase>::class.java)
//            val groupObjOriginal: Array<LeagueStandingsBase> =
//                gson.fromJson(jsonObj, Array<LeagueStandingsBase>::class.java)
//
//
//            bundle.putParcelableArray("leagueStanding", groupObjOriginal)
//            bundle.putParcelableArray("leagueStandingGroup", groupObj)
//
//
//            return leagueInfoBase
//
//        }




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
        Log.d(TAG,"leagueStanding======= ${response.leagueStanding}")

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






