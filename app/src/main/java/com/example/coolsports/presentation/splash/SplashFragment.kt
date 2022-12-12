package com.example.coolsports.presentation.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentSplashBinding
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    val TAG: String = "SplashFragment"
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private lateinit var timer: CountDownTimer
    private  var webBrowser= false

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
        goToNext()


    }



    fun goToNext() {
        Handler().postDelayed({
            lifecycleScope.launchWhenResumed {

//                if (findNavController().currentDestination?.id == R.id.SplashFragment)
//                    navController.navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 2000)

    }



}