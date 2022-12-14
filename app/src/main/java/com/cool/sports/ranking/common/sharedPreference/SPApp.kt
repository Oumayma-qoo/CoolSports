package com.cool.sports.ranking.common.sharedPreference

import android.content.Context
import android.os.Build
import android.text.TextUtils
import com.cool.sports.ranking.common.constant.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class SPApp @Inject constructor(@ApplicationContext val context: Context) {


    var language: String
        get() {
            val language = context.loadSp(Constants.SharedPreferenceKeys.LANGUAGE) ?:""
            return if (TextUtils.isEmpty(language)) {
                val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    context.resources.configuration.locales.get(0)
                } else {
                    context.resources.configuration.locales.get(0)
                }

                when (locale.language) {
                    "cn" -> {
                        Constants.SharedPreferenceKeys.CHINESE
                    }
                    "en" -> {
                        Constants.SharedPreferenceKeys.ENGLISH
                    }

                    "vi" -> {
                        Constants.SharedPreferenceKeys.VIETNAMESE
                    }
                    "th" -> {
                        Constants.SharedPreferenceKeys.TAI
                    }
                    else -> {
                        Constants.SharedPreferenceKeys.CHINESE
                    }
                }
            } else {
                language
            }
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.LANGUAGE, value)

    var init: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.INIT) ?: false
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.INIT, value)


    var showPopUp: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.SHOW_POPUP) ?: false
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.SHOW_POPUP, value)


    var WEB_OPENED: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.WEB_OPENED) ?: false
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.WEB_OPENED, value)

    var WEB_OPTION: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.WEB_OPTION) ?: false
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.WEB_OPTION, value)


    var Timer: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.TIMER) ?: true
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.TIMER, value)

    var web_was_opened: Boolean
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.WEB_WAS_OPENED) ?: false
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.WEB_WAS_OPENED, value)
    var URL: String
        get() {
            return context.loadSp(Constants.SharedPreferenceKeys.URL) ?: ""
        }
        set(value) = context.saveSp(Constants.SharedPreferenceKeys.URL, value)

}