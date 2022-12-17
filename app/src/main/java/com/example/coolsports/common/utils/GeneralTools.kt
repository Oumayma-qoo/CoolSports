package com.example.coolsports.common.utils

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import com.example.coolsports.BuildConfig
import com.example.coolsports.R
import java.util.*

object GeneralTools {
    fun getLocale(context: Context): String {
        return SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.LOCALE_KEY,SharedPreference.ENGLISH,context)
    }
    fun setLocale(context: Context, locale:String) {
        SharedPreference.getInstance().saveStringToPreferences(SharedPreference.LOCALE_KEY,locale,context)
    }
    fun exitDialog(activity: Activity){
        val dialog= Dialog(activity, android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.exit_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<View>(R.id.yes_bt).setOnClickListener {
            activity.finish()
        }
        dialog.findViewById<View>(R.id.no_bt).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun shareApp(activity: Activity){
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                activity.getResources().getString(R.string.app_name).toString() + " App"
            )
            var shareMessage = """
                 
                 Let me recommend you this application
                 
                 
                 """.trimIndent()
            shareMessage =
                """
                 ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                 
                 
                 """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            activity.startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun feedback(activity: Activity){

        val i = Intent(Intent.ACTION_SEND)
        i.type = "*/*"
        // i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(crashLogFile));
        // i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(crashLogFile));
        i.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf<String>(activity.getResources().getString(R.string.account_email))
        )
        i.putExtra(
            Intent.EXTRA_SUBJECT,
            activity.getResources().getString(R.string.app_name).toString() + " FeedBack"
        )

        try {
            activity.startActivity(createEmailOnlyChooserIntent(activity,i, "Send via email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                activity,
                "There is no email client installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun createEmailOnlyChooserIntent(
        activity: Activity,
        source: Intent?,
        chooserTitle: CharSequence?
    ): Intent? {
        val intents = Stack<Intent>()
        val i = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",
                "", null
            )
        )
        val activities: List<ResolveInfo> =activity.getPackageManager()
            .queryIntentActivities(i, 0)
        for (ri in activities) {
            val target = Intent(source)
            target.setPackage(ri.activityInfo.packageName)
            intents.add(target)
        }
        return if (!intents.isEmpty()) {
            val chooserIntent = Intent.createChooser(
                intents.removeAt(0),
                chooserTitle
            )
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                intents.toTypedArray<Parcelable>()
            )
            chooserIntent
        } else {
            Intent.createChooser(source, chooserTitle)
        }
    }

    fun privacyPolicy(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.privacy_policy_link)))
        context.startActivity(browserIntent)
    }

    fun rateUs(activity: Activity) {

        val appPackageName: String = activity.packageName
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "http://play.google.com/store/apps/details?id="
                                + appPackageName
                    )
                )
            )
        }
    }
}
