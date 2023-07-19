package com.example.whitedragonvpn.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MainFragment", "onViewCreated()")
        val bottomNavigationView =
            view.findViewById<BottomNavigationView>(R.id.mainBottomNavigationView)
        val navController =
            (childFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment)
                .navController
        bottomNavigationView.setupWithNavController(navController)
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, _ ->
            WindowInsetsCompat.CONSUMED
        }

        lifecycleScope.launch {
            NetworkErrorHolder.error.collect {
                it?.let {
                    val error = it as NetworkResult.GenericError
                    makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                    NetworkErrorHolder.setError(null)
                }
            }
        }

        lifecycleScope.launch {
            applicationComponent.dialogManager.firstConnectionDialog.collect { intentPrepare ->
                intentPrepare?.let { intent ->
                    startActivity(intent)
//                    startActivityIfNeeded(intent, 0)
                }
            }
        }
    }
}