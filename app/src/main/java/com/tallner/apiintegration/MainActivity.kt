package com.tallner.apiintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class MainActivity : AppCompatActivity() {

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

        firebaseCodes()
    }

    internal fun firebaseCodes() {
        val db = Firebase.firestore

        val user = hashMapOf(
            "username" to "ctallner@hotmail.com",
            "password" to "123456"
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("christiantallner", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("christiantallner", "Error adding document", e)
            }
        // läsa
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("christiantallner", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("christiantallner", "Error getting documents.", exception)
            }

        //listeners
        val docRef = db.collection("test").document("nDryrE2ol0pCCMZiShti")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("christiantallner", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d("christiantallner", "Current data: ${snapshot.data}")
            } else {
                Log.d("christiantallner", "Current data: null")
            }
        }
    }
}

