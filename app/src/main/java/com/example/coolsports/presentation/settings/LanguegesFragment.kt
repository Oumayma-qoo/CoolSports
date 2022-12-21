package com.example.coolsports.presentation.settings

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coolsports.R
import com.example.coolsports.common.constant.Constants.SharedPreferenceKeys.CHINESE
import com.example.coolsports.common.constant.Constants.SharedPreferenceKeys.ENGLISH
import com.example.coolsports.common.constant.Constants.SharedPreferenceKeys.INDONESIAN
import com.example.coolsports.common.constant.Constants.SharedPreferenceKeys.VIETNAMESE
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.ContextUtils
import com.example.coolsports.common.utils.GeneralTools
import com.example.coolsports.common.utils.SharedPreference
import com.example.coolsports.common.utils.SharedPreference.THAI
import com.example.coolsports.databinding.FragmentLanguegesBinding
import com.example.coolsports.presentation.MainActivity
import java.util.*

class LanguegesFragment : Fragment() {
    private var _binding: FragmentLanguegesBinding? = null
    private val binding get() = _binding!!
    private lateinit var sp:SPApp

    companion object {
        fun newInstance() = LanguegesFragment()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguegesBinding.inflate(inflater, container, false)

        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }
         sp = SPApp(requireContext())

        setupView()
        binding.languagesRG.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){

                R.id.englishRB -> {
                    sp.language = ENGLISH
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()
                }

                R.id.chineseRB -> {
                    sp.language = CHINESE
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()
                }

                R.id.indonesianRB -> {
                    sp.language = INDONESIAN
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()                }

                R.id.vietnameseRB -> {
                    sp.language = VIETNAMESE
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()                }

                R.id.thaiRB -> {
                    sp.language = THAI
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity!!.finish()
                }
            }

        }

    }
    private fun setupView() {
        when (sp.language) {
           ENGLISH -> binding.englishRB.isChecked = true
           CHINESE ->  binding.chineseRB.isChecked = true
            VIETNAMESE -> binding. indonesianRB.isChecked = true
            INDONESIAN ->  binding.vietnameseRB.isChecked = true
            INDONESIAN ->  binding.thaiRB.isChecked = true
        }

    }




}