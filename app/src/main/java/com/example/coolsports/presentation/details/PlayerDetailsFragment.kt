package com.example.coolsports.presentation.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coolsports.R
import com.example.coolsports.databinding.FragmentLeagueDetailsBinding
import com.example.coolsports.databinding.FragmentPlayerDetailsBinding

class PlayerDetailsFragment : Fragment() {

    private var _binding: FragmentPlayerDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

}