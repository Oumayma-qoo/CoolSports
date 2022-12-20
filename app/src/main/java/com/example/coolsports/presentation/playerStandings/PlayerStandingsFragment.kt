package com.example.coolsports.presentation.playerStandings

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
import com.example.coolsports.databinding.FragmentPlayerStandingBinding
import com.example.coolsports.databinding.FragmentPlayerStandingsBinding
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.player.BasePlayerStanding
import com.example.coolsports.domain.model.player.Player
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PlayerStandingsFragment() : BaseFragment() {
    val TAG: String = "HomeFragment"
    private var _binding: FragmentPlayerStandingBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var playerList = ArrayList<Player>()
    var topPlayerList = ArrayList<Player>()
    private val viewModel by viewModels<PlayerStandingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerStandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initObserver()


        lifecycleScope.launch {
            viewModel.getPlayerStanding(88, "0")

        }
        goToNext()

    }


    fun goToNext() {
        binding.firstContainer .setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.PlayerStandingsFragment)
                findNavController().navigate(R.id.action_playerStandingFragment_to_playerDetailFragment)
        }


    }

    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED

        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun handleState(state: PlayerStandingScreenState) {
        when (state) {
            is PlayerStandingScreenState.IsLoading -> handleIsLoadingState(state.isLoading)
            is PlayerStandingScreenState.Response -> handlePlayerStandingsResponse(state.playerStanding)
            is PlayerStandingScreenState.NoInternetException -> handleNetworkFailure(state.message)
            is PlayerStandingScreenState.GeneralException -> handleException(state.message)
            is PlayerStandingScreenState.StatusFailed -> handleFailure(state.message)
            else -> {
                Log.d(TAG, " no state run ")
            }
        }
    }

    private fun handlePlayerStandingsResponse(response: BasePlayerStanding) {
        Log.d(TAG, " response success  " + response.list.count())
        playerList.addAll(response.list)

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