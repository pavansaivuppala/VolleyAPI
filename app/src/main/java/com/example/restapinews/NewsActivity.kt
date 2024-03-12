package com.example.restapinews

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.restapinews.news.NewsResponse
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restapinews.Constant.BASE_URL_NEWS_SOURCE
import com.example.restapinews.databinding.ActivityNewsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendGetTypeRequestUsingHeader()
    }

    private fun sendGetTypeRequestUsingHeader() {
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.GET,
            BASE_URL_NEWS_SOURCE,
            {
                //Success block
                val token = object : TypeToken<NewsResponse>() {}
                val newsResponse = Gson().fromJson(it, token)

                if (newsResponse.status == "ok") {
                    Log.i("tag","${newsResponse.news[0].description}")
                }
            }, {
                // error block
                Log.i("tag","${it.toString()}")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Authorization"] = "ehTUs_L7VNOevxSsW301L3Y6KhOmJ573Grs-VKu--uPjKPZF"
                return header
            }
        }
        requestQueue.add(stringRequest)
    }
}

