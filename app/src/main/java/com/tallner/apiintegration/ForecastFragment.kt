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

class ForecastFragment : Fragment() {
    private var city = "lund"
    private var country = "sweden"
    private var url =
        "https://api.openweathermap.org/data/2.5/forecast?q="+
                city+ "," + country +
                "&appid=35ec794bb2d83a735fb4edfd249390a7"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {

            val tv_dates = arrayOf(
                view.findViewById<TextView>(R.id.tv_date_0),
                view.findViewById(R.id.tv_date_1),
                view.findViewById(R.id.tv_date_2),
                view.findViewById(R.id.tv_date_3),
                view.findViewById(R.id.tv_date_4)
            )
            val tv_temps = arrayOf(
                view.findViewById<TextView>(R.id.tv_temp_0),
                view.findViewById(R.id.tv_temp_1),
                view.findViewById(R.id.tv_temp_2),
                view.findViewById(R.id.tv_temp_3),
                view.findViewById(R.id.tv_temp_4)
            )
            // on below line we are creating a variable for our
            // request queue and initializing it.
            val queue: RequestQueue = Volley.newRequestQueue(view.context)

            val JsonRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val temp = response.getJSONArray("list")
                        var y = 0
                        for (i in 0..39 step 7){

                            var tmp = temp.getJSONObject(i).getJSONObject("main").getString("temp").toFloat().toInt()
                            tmp -= 272
                            tv_temps[y].text = tmp.toString() + " °C"
                            tv_dates[y].text = temp.getJSONObject(i).getString("dt_txt")
                            y++
                        }

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                },
                { error ->

                })
            queue.add(JsonRequest)
        }
    }
}