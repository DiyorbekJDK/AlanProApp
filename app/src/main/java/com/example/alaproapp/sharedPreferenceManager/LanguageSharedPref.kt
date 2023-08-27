package com.example.alaproapp.sharedPreferenceManager

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguageSharedPref(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)


    fun saveLanguage(language: String) {
        val editor = sharedPref.edit()
        editor.putString("lan",language)
        editor.apply()
    }

    fun getLanguage(): String {
        return sharedPref.getString("lan","en")?:"en"
    }

}