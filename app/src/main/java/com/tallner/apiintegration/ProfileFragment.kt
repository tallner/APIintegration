package com.tallner.apiintegration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    private lateinit var city: TextView
    private lateinit var country: TextView
    private lateinit var user: TextView
    private val myFirebaseHelper = FirebaseHelper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            city = view.findViewById(R.id.tv_myHometown)
            country = view.findViewById(R.id.tv_myCountry)
            user = view.findViewById(R.id.tv_myusername)

            myFirebaseHelper.getUserData("Christian", object : FirebaseHelper.GetUserCallback {
                override fun onCallback(userdata: User) {
                    city.text = userdata.city
                    country.text = userdata.country
                    user.text = userdata.name
                    return
                }
            })
        }
    }
}