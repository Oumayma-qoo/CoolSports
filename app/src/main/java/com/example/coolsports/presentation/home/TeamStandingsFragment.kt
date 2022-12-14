package com.example.coolsports.presentation.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentLeagueInfoBinding
import com.example.coolsports.databinding.FragmentTeamStandingsBinding

class TeamStandingsFragment : Fragment() {

    private var _binding: FragmentTeamStandingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

}