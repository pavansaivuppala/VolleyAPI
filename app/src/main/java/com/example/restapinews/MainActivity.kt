package com.example.restapinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restapinews.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.example.restapinews.Constant.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  initViews()
    }
}
/*
    private fun initViews() {
        binding.btnNext.setOnClickListener {
            makeAPICall()
        }
    }

    private fun makeAPICall() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)


        val request = StringRequest(
            Request.Method.GET,
            URL,
            { response ->
                val typeToken = object : TypeToken<NewsResponse>() {}.type
                val gson = Gson()
                val newsResponse: NewsResponse = gson.fromJson(response, typeToken)
                setImageOfNews(newsResponse.articles)
            },
            { error ->
                Log.e("TAG", "Error: $error")
            }
        )

        requestQueue.add(request)
    }

    private fun setImageOfNews(articles: List<Article>) {
        val firstArticle = articles[0]
        binding.author.text = firstArticle.author
        Picasso.get().load(firstArticle.urlToImage).into(binding.imageOfNews)
    }
}
*/
