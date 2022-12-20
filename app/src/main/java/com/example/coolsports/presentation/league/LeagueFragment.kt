package com.example.coolsports.presentation.league

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.coolsports.R
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.constant.Constants.URL
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.common.utils.ListResponse
import com.example.coolsports.common.utils.ListResponse.adsArrayList
import com.example.coolsports.databinding.FragmentLeagueBinding
import com.example.coolsports.domain.model.league.LeagueModel
import com.example.coolsports.presentation.base.BaseFragment
import com.example.coolsports.presentation.webView.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class LeagueFragment : BaseFragment() {

    val TAG: String = "LeagueFragment"
    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LeagueViewModel>()
    var bundle = Bundle()
    var listLeague = mutableListOf<LeagueModel>()

    var leagueId: Int = 0
    lateinit var sp: SPApp


    private val leagueListAdapter by lazy { LeagueListAdapter() }
    private lateinit var timer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.PREMIERE_LEAGUE),
                Constants.PREMIERE_LEAGUE,"England","https://cdn1.77577cf.com/img-zq/Image/league_match/images/164577482086.png?win007=sell"
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.LA_LIGA), Constants.LA_LIGA,"Spain","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20200415071847.jpg?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.SERIE_A), Constants.SERIE_A,"Italy","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20190815175316.png?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.BUNDESLIGA), Constants.BUNDESLIGA,"Germany","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210630142615.png?win007=sell"))
        listLeague.add(LeagueModel(resources.getString(R.string.LIGUE_1), Constants.LIGUE_1,"France","https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210416104457.png?win007=sell"))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CHINESE_SUPER_LEAGUE),
                Constants.CHINESE_SUPER_LEAGUE,
                "China"
                ,
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20140111105821.jpg?win007=sell"

            ,
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AFC_CHAMPIONS_LEAGUE),
                Constants.AFC_CHAMPIONS_LEAGUE,
                "Asia",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210104160334.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.ASIAN_QUALIFIERS),
                Constants.ASIAN_QUALIFIERS,"International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20210104160247.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.SOUTH_AMERICAN_QUALIFIER),
                Constants.SOUTH_AMERICAN_QUALIFIER, "International","https://cdn1.77577cf.com/img-zq/Image/league_match/images/2008571027162493.jpg?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_QUALIFIER),
                Constants.EUROPEAN_QUALIFIER,
                "International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20085710192243008.jpg?win007=sell"
            )
        )
        listLeague.add(LeagueModel(resources.getString(R.string.WORLD_CUP), Constants.WORLD_CUP,"International","https://cdn1.77577cf.com/img-zq/Image/league_match/images/164885996047.png?win007=sell"))
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.EUROPEAN_CUP),
                Constants.EUROPEAN_CUP,
                "International",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/164885996047.png?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.CONFEDERATIONS_CUP),
                Constants.CONFEDERATIONS_CUP,
                "Europe",

                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20170531172156.jpg?win007=sell"
            )
        )
        listLeague.add(
            LeagueModel(
                resources.getString(R.string.AMERICAS_CUP),
                Constants.AMERICAS_CUP,
                "Americas",
                "https://cdn1.77577cf.com/img-zq/Image/league_match/images/20200411095945.jpg?win007=sell"
            )
        )
        leagueListAdapter.submitList(listLeague)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        initRV()
        goToSettings()
        sp= SPApp(context!!)

        checkInit()
        sp.showPopUp= true
        showPopup()
        slideImage()
        clickOnWeb()
        checkUser()

    }

    private fun goToSettings(){
        binding.settingsIcon.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                navController.navigate(
                    R.id.action_LeagueFragment_to_navigation_settings)
        }
    }

    private fun initRV() {
        binding.leagueRecyclerView.adapter = leagueListAdapter
        binding.leagueRecyclerView.layoutManager = LinearLayoutManager(activity)

        leagueListAdapter.setItemTapListener(object : LeagueListAdapter.OnItemTap {
            override fun onTap(position: Int) {
                leagueId = leagueListAdapter.getLeagueId(position)
                bundle.putInt("leagueId", leagueId)
                if (findNavController().currentDestination?.id == R.id.LeagueFragment)
                    navController.navigate(
                        R.id.action_leagueFragment_to_leagueInfoFragment,
                        bundle
                    )
            }
        })
    }

    // click on web icon
    fun clickOnWeb(){
        val sp=SPApp(requireContext())
        binding.ivWebHOme.setOnClickListener {
            if (sp.WEB_OPENED) {
                val url = sp.URL
                if (sp.WEB_OPTION)
                    goToWeb(url, "0")
                else
                    goToWeb(url, "1")
            }
        }
    }

    // if user was on web page
    fun checkUser(){
        val url= sp.URL
        bundle.putString(URL, url)

        Log.d("url1===========", sp.URL)


        if (sp.web_was_opened) {

            stopTimer()
            activity.let {
                val intent = Intent(it, WebViewActivity::class.java)
                startActivity(intent)
            }

        }


    }

    private fun checkInit()
    {
        if( !ListResponse.redirect_url.isNullOrEmpty() && ! ListResponse.redirect_url.isNullOrEmpty())
            goToWeb(ListResponse.redirect_url!!, ListResponse.open_type!!)

    }





    // slider
    fun slideImage()
    {

        val imageList = ArrayList<SlideModel>()
        for (banner in adsArrayList) {
            imageList.add(SlideModel(banner.image_path, ScaleTypes.CENTER_CROP))
        }

        if (imageList.isNotEmpty()) {
            binding.imageSlider.startSliding(4000)
            binding.imageSlider.visibility = View.VISIBLE
            binding.imageSlider.setImageList(imageList)
        } else
            binding.imageSlider.visibility = View.GONE


        binding.imageSlider.setItemClickListener(object : ItemClickListener {


            override fun onItemSelected(position: Int) {
                    goToWeb(
                        adsArrayList[position].redirect_url!!,
                        adsArrayList[position].open_type!!
                    )

            }

        })




    }



    private fun goToWeb(redirectUrl: String, openType: String) {
        stopTimer()
        sp.WEB_OPENED=true
        sp.URL= redirectUrl

        if (openType == "0") { // open web view
            sp.WEB_OPTION=true
            activity.let {
                val intent = Intent(it, WebViewActivity::class.java)
                startActivity(intent)
            }

        }
        else
        {
            // open browser
            sp.WEB_OPTION=false
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    redirectUrl
                )
            )
            startActivity(browserIntent)
        }
    }
    private fun startTimer(time: Long) {

        if (this::timer.isInitialized)
            timer.cancel()

        timer = object : CountDownTimer(time * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("QOO", ".....tick tick timer is running....")
            }

            override fun onFinish() {
                if (ListResponse.repeat_status == 1)
                    showPopup()
            }
        }
        timer.start()
    }



    private fun showPopup() {

        if (ListResponse.prompt_title != null && sp.showPopUp)
            if (ListResponse.prompt_title!!.isNotEmpty()) {
                messageDialog(requireActivity())
            }
        hideLoading()


    }
    private fun messageDialog(activity: Activity) {
        val dialog = Dialog(activity, android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.settings_popup)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<TextView>(R.id.heading_text).text = ListResponse.prompt_title
        dialog.findViewById<TextView>(R.id.body_text).text = ListResponse.prompt_message

        dialog.findViewById<ImageView>(R.id.ivClosePopup).setOnClickListener {
            if (ListResponse.repeat_status == 1)
                startTimer(ListResponse.repeat_time.toLong())
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.yes_bt).text = ListResponse.button
        dialog.findViewById<View>(R.id.yes_bt).setOnClickListener {
            goToWeb(ListResponse.redirect_url!!, ListResponse.open_type!!)
            dialog.dismiss()
        }
        dialog.show()
    }



    private fun stopTimer() {
        if (this::timer.isInitialized)
            timer.cancel()
        sp.Timer= true


    }

    override fun onResume() {
        // check if web view or web browser is opened
        if (sp.WEB_OPENED) {
            binding.ivWebHOme.visibility = View.VISIBLE
        }
        else
            binding.ivWebHOme.visibility = View.GONE

        // check if timer was stop
        if (sp.Timer) {
            if (this::timer.isInitialized)
                timer.cancel() // if there is already timer running
            // if webPage was ON don't start timer
            if (!sp.web_was_opened) {
                if (ListResponse.repeat_time != 0)
                    startTimer(ListResponse.repeat_time.toLong())
                sp.Timer=false
            }
        }

        super.onResume()
    }


}



