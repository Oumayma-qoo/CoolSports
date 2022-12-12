package com.example.coolsports.common.sharedPreference

import android.content.Context
import android.os.Build
import android.text.TextUtils
import com.example.coolsports.common.constant.Constants
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
                        Constants.SharedPreferenceKeys.VIETNAMESE
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





}