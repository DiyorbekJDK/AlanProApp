package com.example.alaproapp.sharedPreferenceManager

import android.content.Context
import android.content.SharedPreferences

class ModeSharedPref(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("ModeSharedPref", Context.MODE_PRIVATE)


    fun saveMode(mode: String) {
        val editor = sharedPref.edit()
        editor.putString("mode", mode)
        editor.apply()
    }

    fun getMode(): String {
        return sharedPref.getString("mode", "light") ?: "light"
    }
}