package com.cool.sports.ranking.presentation.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.cool.sports.ranking.R
import com.cool.sports.ranking.common.utils.ApiRequestBannerAds
import com.cool.sports.ranking.databinding.FragmentSplashBinding
import com.cool.sports.ranking.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    val TAG: String = "SplashFragment"
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        requestBanner()
    }

    fun goToNext() {
        Handler().postDelayed({
            if (findNavController().currentDestination?.id == R.id.SplashFragment)
                navController.navigate(R.id.action_splashFragment_to_leagueFragment)
        }, 1000)

    }

    private fun requestBanner() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                ApiRequestBannerAds.sentReqBanner(requireContext())

            } catch (e: Exception) {
                println(e.message)

            } finally {
                withContext(Dispatchers.Main) {
                    lifecycleScope.launchWhenResumed {
                        goToNext()

                    }
                }
            }
        }


    }
}