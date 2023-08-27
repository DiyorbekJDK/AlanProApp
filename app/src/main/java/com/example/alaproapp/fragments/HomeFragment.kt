package com.example.alaproapp.fragments

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.alaproapp.R
import com.example.alaproapp.databinding.FragmentHomeBinding
import com.example.alaproapp.sharedPreferenceManager.LanguageSharedPref
import com.example.alaproapp.util.LocalHelper
import com.google.android.material.button.MaterialButton
import java.util.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alanBtn.setOnLongClickListener {
            binding.alanBtn.setBackgroundColor(randomColor())
            return@setOnLongClickListener true
        }
        binding.alanBtn.setOnClickListener {
            showPhotoDialog()
        }
        binding.ytbBtn.setOnClickListener {
            goToLink("http://www.youtube.com/@user-el5ib2sd6r")
        }
        binding.tgBtn.setOnClickListener {
            goToLink("https://t.me/Alan_pro_kaktus")
        }
        binding.tgTalkGroupBtn.setOnClickListener {
            goToLink("https://t.me/Alan_pro_kaktusss")
        }
        binding.tgBotBtn.setOnClickListener {
            goToLink("https://t.me/AlanProKaktusBot")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun randomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    private fun goToLink(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun showPhotoDialog() {

        val layoutInflater = LayoutInflater.from(requireContext())
        val mydialog = layoutInflater.inflate(R.layout.photos_dialog_layout, null)
        val btnDone: MaterialButton = mydialog.findViewById(R.id.btndone)
        val firstPic: ImageView = mydialog.findViewById(R.id.imageView3)
        val secondPic: ImageView = mydialog.findViewById(R.id.prevu)
        val thirdPic: ImageView = mydialog.findViewById(R.id.imageView2)
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.apply {
            setView(mydialog)

            btnDone.setOnClickListener {
                alertDialog.dismiss()
            }
            firstPic.setOnClickListener {
                fullPhotoDialog(R.drawable.channel_photo)
            }
            secondPic.setOnClickListener {
                fullPhotoDialog(R.drawable.new__channel_photo)
            }
            thirdPic.setOnClickListener {
                fullPhotoDialog(R.drawable.second__channel_photo)
            }

        }.show()

    }

    private fun fullPhotoDialog(img: Int) {
        val lay = layoutInflater.inflate(R.layout.full_photo_layout, null)
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialog.apply {
            setView(lay)
            val image: ImageView = lay.findViewById(R.id.img)
            image.setImageResource(img)
        }.show()
    }


}