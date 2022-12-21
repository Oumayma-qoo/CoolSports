package com.example.coolsports.presentation

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.coolsports.R
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.ContextUtils
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import com.example.coolsports.common.utils.GeneralTools
import com.example.coolsports.presentation.league.LeagueFragment
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment_content_main)

        setupNavGraph()
    }


    private fun setupNavGraph() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
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


    override fun onBackPressed() {
        if (navController.currentDestination?.id==R.id.LeagueFragment) {
            GeneralTools.exitDialog(this@MainActivity)
        }
        else {
            super.onBackPressed()
        }

    }


}