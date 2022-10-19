package com.tallner.apiintegration

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class FirebaseDocument(
    val name:String,
    val id:String,
    val data: Map<String,Any>
)

data class User(
    var name: String = "",
    var city: String = "",
    var country: String = ""
)

class FirebaseHelper() {
    val db = Firebase.firestore

    interface GetUserCallback {
        fun onCallback(userData: User)
    }

    interface FirestoreCallback {
        fun onCallback(firebaseDoc: List<FirebaseDocument>)
    }


    val user = hashMapOf(
        "username" to "Christian",
        "password" to "123456",
        "city" to "flyinge",
        "country" to "sweden"
    )

    fun addUser(user:HashMap<String,String>){
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("myLog", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("myLog", "Error adding document", e)
            }
    }

    fun getUserDocuments(firestoreCallback:FirestoreCallback)  {
        val firebaseDocList: MutableList<FirebaseDocument> = mutableListOf()

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    firebaseDocList.add(FirebaseDocument("users",document.id,document.data))
                }
                firestoreCallback.onCallback(firebaseDocList)
            }
            .addOnFailureListener { exception ->
                Log.w("myLog", "Error getting documents.", exception)
            }
    }

    fun getUserData(username:String,getUserCallback: GetUserCallback) {
        val userdata = User()

        getUserDocuments(object : FirestoreCallback {
            override fun onCallback(userlist: List<FirebaseDocument>) {
                userlist.forEach{
                    if (it.data["username"]?.equals(username) == true){
                        userdata.name = it.data["username"].toString()
                        userdata.city = it.data["city"].toString()
                        userdata.country = it.data["country"].toString()

                        getUserCallback.onCallback(userdata)
                        return@forEach
                    }
                }
            }
        })
    }

    fun addListener(){
        //listeners
        val docRef = db.collection("test").document("nDryrE2ol0pCCMZiShti")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("myLog", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d("myLog", "Current data: ${snapshot.data}")
            } else {
                Log.d("myLog", "Current data: null")
            }
        }
    }
}