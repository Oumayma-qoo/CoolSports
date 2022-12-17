package com.example.coolsports.presentation.settings

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coolsports.R
import com.example.coolsports.common.utils.GeneralTools
import com.example.coolsports.common.utils.SharedPreference
import com.example.coolsports.databinding.FragmentLanguegesBinding

class LanguegesFragment : Fragment() {
    private var _binding: FragmentLanguegesBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = LanguegesFragment()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguegesBinding.inflate(inflater, container, false)

        when (context?.let { GeneralTools.getLocale(it) }) {
            SharedPreference.ENGLISH -> {
                binding.englishRB.isChecked = true
            }
            SharedPreference.CHINESE -> {
                binding.chineseRB.isChecked = true
            }
            SharedPreference.INDONESIAN -> {
                binding.indonesianRB.isChecked = true
            }
            SharedPreference.VIETNAMESE -> {
                binding.vietnameseRB.isChecked = true
            }
            SharedPreference.THAI -> {
                binding.thaiRB.isChecked = true
            }
        }
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.languagesRG.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){

                R.id.englishRB -> {
                    changeLanguage(SharedPreference.ENGLISH)
                }

                R.id.chineseRB -> {
                    changeLanguage(SharedPreference.CHINESE)
                }

                R.id.indonesianRB -> {
                    changeLanguage(SharedPreference.INDONESIAN)
                }

                R.id.vietnameseRB -> {
                    changeLanguage(SharedPreference.VIETNAMESE)
                }

                R.id.thaiRB -> {
                    changeLanguage(SharedPreference.THAI)
                }
            }

        }

    }



    private fun changeLanguage(lang: String) {
        context?.let { GeneralTools.setLocale(it, lang) }
//        SharedPreference.getInstance().saveBooleanToPreferences(Constants.LangChanged,true, context)
//        startActivity(Intent(context, SplashActivity::class.java))
    }


}