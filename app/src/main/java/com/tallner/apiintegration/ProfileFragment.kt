package com.tallner.apiintegration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class ProfileFragment : Fragment() {
    private lateinit var city: TextView
    private lateinit var country: TextView
    private lateinit var user: TextView

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

            city.text = "Flyinge"
            country.text = "Sweden"
            user.text = "Christian"



        }
    }
}