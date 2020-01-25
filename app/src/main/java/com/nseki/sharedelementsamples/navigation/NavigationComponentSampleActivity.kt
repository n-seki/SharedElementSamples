package com.nseki.sharedelementsamples.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.nseki.sharedelementsamples.R
import com.nseki.sharedelementsamples.databinding.ActivityNavigationBinding

class NavigationComponentSampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NavigationComponentSampleActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController
        val startDestinationArgs = bundleOf("image_res" to R.drawable.ic_android)
        navController.setGraph(navController.graph, startDestinationArgs)

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(navController.graph)
        )
    }
}