package com.example.coolsports.presentation

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.example.coolsports.R
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.ContextUtils
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import com.example.coolsports.presentation.league.LeagueFragment
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavGraph()
    }


    private fun setupNavGraph() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    override fun attachBaseContext(newBase: Context) {
        try {
            val sp = SPApp(newBase)
            val lang = sp.language
            val localeUpdatedContext: ContextWrapper =
                ContextUtils.updateLocale(newBase, Locale(lang))
            super.attachBaseContext(localeUpdatedContext)
        } catch (e: Exception) {
            super.attachBaseContext(newBase)
        }

    }





}