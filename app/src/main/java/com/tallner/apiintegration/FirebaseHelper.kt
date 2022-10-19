package com.tallner.apiintegration

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseHelper() {
    fun firebaseCodes() {
        val db = Firebase.firestore

        val user = hashMapOf(
            "username" to "Christian",
            "password" to "123456",
            "city" to "flyinge",
            "country" to "sweden"
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