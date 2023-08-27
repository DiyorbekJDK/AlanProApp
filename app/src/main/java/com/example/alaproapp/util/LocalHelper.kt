package com.example.alaproapp.util

import android.content.Context
import android.os.Build
import com.example.alaproapp.sharedPreferenceManager.LanguageSharedPref
import java.util.Locale

class LocalHelper(private val context: Context) {
    fun setLanguage(language: String) {
        val sharedPref = LanguageSharedPref(context)
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        config.setLayoutDirection(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config)
        }
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}