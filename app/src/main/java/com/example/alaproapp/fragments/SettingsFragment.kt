package com.example.alaproapp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.alaproapp.R
import com.example.alaproapp.activity.MainActivity
import com.example.alaproapp.activity.SplashActivity
import com.example.alaproapp.databinding.FragmentNotificationsBinding
import com.example.alaproapp.sharedPreferenceManager.LanguageSharedPref
import com.example.alaproapp.sharedPreferenceManager.ModeSharedPref
import com.example.alaproapp.util.LocalHelper

class SettingsFragment : Fragment() {
    private val sharedPref by lazy { LanguageSharedPref(requireContext()) }
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private var isNight: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val mod: String
        val modeSharedPref = ModeSharedPref(requireContext())
        mod = modeSharedPref.getMode()

        if (mod == "Night") {
            binding.switch3.isChecked = true
            isNight = true
        }
        binding.switch3.setOnClickListener {
            if (isNight){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                modeSharedPref.saveMode("Light")
                binding.switch3.isChecked = false
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                modeSharedPref.saveMode("Night")
                binding.switch3.isChecked = true
            }
        }

        binding.btnLan.setOnClickListener {
            val languageList = arrayOf("English", "Русский", "Uzbek", "тоҷикӣ", "қазақ")
            val di = AlertDialog.Builder(requireContext())



            di.apply {
                setTitle(getString(R.string.change_language))
                setPositiveButton(getString(R.string.close_txt)) { dialg, _ ->
                    dialg.dismiss()
                }
                setSingleChoiceItems(languageList, -1) { _: DialogInterface?, lan: Int ->
                    when (lan) {
                        0 -> {
                            showExitDialog("en")
                        }

                        1 -> {
                            showExitDialog("ru")
                        }

                        2 -> {
                            showExitDialog("uz")
                        }

                        3 -> {
                            showExitDialog("tj")
                        }

                        4 -> {
                            showExitDialog("kz")
                        }
                    }
                }
            }.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showExitDialog(langg: String) {
        val d = AlertDialog.Builder(requireContext())
        d.apply {
            setTitle(getString(R.string.exit_txt) + "?")
            setMessage(getString(R.string.exit_mess_txt))
            setPositiveButton(getString(R.string.yes_txt)) { _, _ ->
                sharedPref.saveLanguage(langg)
                requireActivity().finish()
            }
            setNegativeButton(getString(R.string.no_txt)) { dia, _ ->
                dia.dismiss()
            }
        }.create().show()
    }

}