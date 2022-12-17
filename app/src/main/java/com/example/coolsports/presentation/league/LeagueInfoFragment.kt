package com.example.coolsports.presentation.league

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.coolsports.databinding.FragmentTeamInfoBinding
import com.example.coolsports.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueInfoFragment : BaseFragment() {

    val TAG: String = "LeagueInfoFragment"
    private var _binding: FragmentTeamInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()

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

        goToNext()

        val leagueId = arguments!!.getInt("LeagueId")
        Log.d(TAG, leagueId.toString())

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


    fun goToNext() {
//        binding.goTeamBtn.setOnClickListener {
//            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
//                navController.navigate(R.id.action_leagueFragment_to_teamStandingsFragment,bundle)
//        }
//        binding.goPlayerBtn.setOnClickListener {
//            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
//                navController.navigate(R.id.action_leagueFragment_to_playerStandingsFragment)
//        }


    }
}




