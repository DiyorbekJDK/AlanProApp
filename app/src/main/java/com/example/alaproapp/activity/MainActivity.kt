package com.example.alaproapp.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alaproapp.R
import com.example.alaproapp.databinding.ActivityMainBinding
import com.example.alaproapp.databinding.FragmentHomeBinding
import com.example.alaproapp.databinding.FragmentNotificationsBinding
import com.example.alaproapp.sharedPreferenceManager.LanguageSharedPref
import com.example.alaproapp.sharedPreferenceManager.ModeSharedPref
import com.example.alaproapp.util.LocalHelper
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val localHelper = LocalHelper(this)
        val sharedPref = LanguageSharedPref(this)
        localHelper.setLanguage(sharedPref.getLanguage())

        val modeSharedPref = ModeSharedPref(this)
        mode = modeSharedPref.getMode()
        if (mode == "Night") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#1CD9F1") // to change status bar color
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.app_color)))
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}