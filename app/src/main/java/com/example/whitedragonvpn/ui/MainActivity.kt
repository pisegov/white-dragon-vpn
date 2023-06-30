package com.example.whitedragonvpn.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import com.example.whitedragonvpn.databinding.ActivityMainBinding
import com.example.whitedragonvpn.ui.base_fragment.VpnConnectionSwitch
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import com.wireguard.android.backend.GoBackend
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),
    VpnConnectionSwitch {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        NetworkErrorHolder.error.observe(this) {
            val error = it as NetworkResult.GenericError
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onSwitchClicked() {

        val tunnelLauncher = App.get(this).applicationComponent.tunnelLauncher

        lifecycleScope.launch {
            val intentPrepare: Intent? = GoBackend.VpnService.prepare(this@MainActivity)
            intentPrepare?.let { intent ->
                startActivityIfNeeded(intent, 0)
            }
            tunnelLauncher.toggleTunnelState()
        }
    }
}