package com.example.coolsports.presentation.home

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
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.databinding.FragmentHomeBinding
import com.example.coolsports.domain.model.match.BaseClassIndexNew
import com.example.coolsports.domain.model.match.Match
import com.example.coolsports.domain.model.match.TeamInfo
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    val TAG: String = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var matchesList = ArrayList<Match>()
    private val viewModel by viewModels<HomeViewModel>()

    private var pageNumber = 1
    private var pageCount = 0
    var teamsList = java.util.ArrayList<TeamInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initObserver()
        val sp = SPApp(requireContext())

        val language = sp.language

        lifecycleScope.launch {
            viewModel.getMatches(language, pageNumber.toString())

        }
        goToNext()
    }
    fun goToNext() {
        binding.leagueTitle.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.HomeFragment)
                navController.navigate(R.id.action_homeFragment_to_leagueFragment)
        }



    }

    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED

        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun handleState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.IsLoading -> handleIsLoadingState(state.isLoading)
            is HomeScreenState.Response -> handleTeamResponse(state.videos)
            is HomeScreenState.NoInternetException -> handleNetworkFailure(state.message)
            is HomeScreenState.GeneralException -> handleException(state.message)
            is HomeScreenState.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private fun handleTeamResponse(response: BaseClassIndexNew) {
//        Log.d(TAG, " response success  " + response.matchList.count())
        pageCount = response.meta.total
        matchesList.addAll(response.matchList)
        handleTeamsData(matchesList)
    }

    private fun handleTeamsData(matchesList: java.util.ArrayList<Match>) {
        for (match in matchesList) {
            if ( match.leagueName.isNotEmpty() && match.homeLogo.isNotEmpty() && match.location.isNotEmpty() ) {
                teamsList.add(
                    TeamInfo(
                        match.leagueName,
                        match.homeLogo,
                        match.location
                    )
                )
            }
//            Log.d(TAG, " response success teamsList  " + teamsList)


        }

    }
    // pagination
    private fun loadMoreMatches() {
        val sp = SPApp(requireContext())

        val language = sp.language
        if (pageNumber > pageCount)
            return
        else {
            pageNumber += 1
            lifecycleScope.launch {
                viewModel.getMatches(language, pageNumber.toString())

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