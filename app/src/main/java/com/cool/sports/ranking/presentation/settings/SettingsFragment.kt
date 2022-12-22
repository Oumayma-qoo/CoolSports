package com.cool.sports.ranking.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cool.sports.ranking.common.utils.GeneralTools
import com.cool.sports.ranking.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {


    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.languageTV.setOnClickListener {
            val action = SettingsFragmentDirections.actionNavigationSettingsToNavigationLanguages()
            findNavController().navigate(action)
        }
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.privacyTV.setOnClickListener {
            GeneralTools.privacyPolicy(requireActivity())
        }

        binding.shareTV.setOnClickListener {
            GeneralTools.shareApp(requireActivity())
        }

        binding.feedbackTV.setOnClickListener {
            GeneralTools.feedback(requireActivity())
        }

        binding.rateTV.setOnClickListener {
            GeneralTools.rateUs(requireActivity())
        }

        binding.exitTV.setOnClickListener {
            GeneralTools.exitDialog(requireActivity())
        }

    }

}