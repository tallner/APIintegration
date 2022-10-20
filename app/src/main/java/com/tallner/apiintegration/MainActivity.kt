package com.tallner.apiintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initiate the bottom navigation component
        val btmNavView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        //setup the fragment manager
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        //setup the bar on top of the app to get the labels from the fragments
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.currentTempFragment,R.id.forecastFragment,R.id.profileFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        //setup the navigation bar to be used with the navigation controller
        btmNavView.setupWithNavController(navController)

        // store the username for the application to use
        val bundle: Bundle? = intent.extras
        userName = bundle?.get("EXTRA_LOGGED_IN_USER") as String


    }

    fun getUsername() : String {
        return userName
    }


}

