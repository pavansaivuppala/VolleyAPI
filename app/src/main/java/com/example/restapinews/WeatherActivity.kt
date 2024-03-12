package com.example.restapinews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restapinews.Constant.API_KEY_WEATHER
import com.example.restapinews.Constant.BASE_URL_WEATHER
import com.example.restapinews.databinding.ActivityWeatherBinding
import com.example.restapinews.weather_app.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendGetTypeRequest("44.314842", "-85.602364")
    }

    private fun sendGetTypeRequest(latitude: String, longitude: String) {
        val requestQueue = Volley.newRequestQueue(this)

        val queryParam = "lat=$latitude&lon=$longitude&appid=$API_KEY_WEATHER"
        val requestUrl = "$BASE_URL_WEATHER?$queryParam"
        val stringRequest = StringRequest(
            Request.Method.GET,
            requestUrl,
            {
                //Success block
                val token = object : TypeToken<WeatherResponse>() {}
                val response = Gson().fromJson(it, token)
                Log.i("tag", response.name)
            }, {
                // error block
                Log.i("tag", it.toString())
            }
        )
        requestQueue.add(stringRequest)
    }
}