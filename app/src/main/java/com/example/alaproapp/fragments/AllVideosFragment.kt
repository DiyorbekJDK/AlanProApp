package com.example.alaproapp.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alaproapp.R
import com.example.alaproapp.activity.PlayerActivity
import com.example.alaproapp.adapter.FullVideoAdapter
import com.example.alaproapp.adapter.ShortVideoAdapter
import com.example.alaproapp.databinding.FragmentDashboardBinding
import com.example.alaproapp.model.YoutubeModel
import com.example.alaproapp.network.RetroInstance
import com.example.alaproapp.sharedPreferenceManager.LanguageSharedPref
import com.example.alaproapp.util.LocalHelper
import com.example.alaproapp.util.NetworkHelper
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllVideosFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val fullVideoAdapter by lazy { FullVideoAdapter() }
    private val shortVideoAdapter by lazy { ShortVideoAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkInternet()


    }


    private fun callApi() {
        RetroInstance.retroInstance().getAllData().enqueue(object : Callback<YoutubeModel> {
            override fun onResponse(call: Call<YoutubeModel>, response: Response<YoutubeModel>) {
                if (response.isSuccessful) {
                    binding.progressBar.isVisible = false
                    fullVideoAdapter.submitList(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<YoutubeModel>, t: Throwable) {
                Log.d("@@@", "onFailure: ${t.message}")
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAlertDialog() {
        val layoutInflater = LayoutInflater.from(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.no_internet_dialog, null)
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setView(dialogView)
        val btn: MaterialButton = dialogView.findViewById(R.id.btnRefresh)
        val btn2: MaterialButton = dialogView.findViewById(R.id.btnExit)
        btn2.setOnClickListener {
            alertDialog.dismiss()
        }
        btn.setOnClickListener {
            checkInternet()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun checkInternet() {
        val network = NetworkHelper(requireContext())
        if (network.isNetworkConnected()) {
            mainCode()
        } else {
            showAlertDialog()
        }
    }

    private fun mainCode() {

        binding.rv.apply {
            adapter = fullVideoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        callApi()
        fullVideoAdapter.onClick = {
            val bundle = bundleOf("item" to it)
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            intent.putExtras(bundle)
            requireContext().startActivity(intent)
        }
    }
}