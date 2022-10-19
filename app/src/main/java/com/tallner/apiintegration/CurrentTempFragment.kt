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
import java.lang.Exception

class CurrentTempFragment : Fragment() {
    private val myFirebaseHelper = FirebaseHelper()
    private lateinit var tvTemperature: TextView
    private lateinit var tvMyCity: TextView
    private var city = "lund"
    private var country = "sweden"
    private var url =
        "https://api.openweathermap.org/data/2.5/weather?q="+
                city+ "," + country +
                "&appid=35ec794bb2d83a735fb4edfd249390a7"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_temp, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {

            var loggedInUser:MainActivity = context as MainActivity

            myFirebaseHelper.getUserData(loggedInUser.toString(), object : FirebaseHelper.GetUserCallback {
                override fun onCallback(userdata: User) {
                    city = userdata.city
                    country = userdata.country
                    return
                }
            })

            var url =
                "https://api.openweathermap.org/data/2.5/weather?q="+
                        city+ "," + country +
                        "&appid=35ec794bb2d83a735fb4edfd249390a7"




            tvMyCity = view.findViewById(R.id.tv_myTemp)
            tvMyCity.text = city.uppercase()

            tvTemperature = view.findViewById(R.id.tv_temperature)

            // on below line we are creating a variable for our
            // request queue and initializing it.
            val queue: RequestQueue = Volley.newRequestQueue(view.context)

            val JsonRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {

                        var tmp = response.getJSONObject("main").getString("temp").toFloat().toInt()
                        tmp -= 272
                        tvTemperature.text = tmp.toString() + " °C"

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                },
                { error ->
                    tvTemperature.text = "Not available"
                })
            queue.add(JsonRequest)
        }
    }
}