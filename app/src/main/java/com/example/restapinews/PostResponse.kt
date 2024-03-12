package com.example.restapinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restapinews.Constant.BASE_URL_DELETE
import com.example.restapinews.Constant.BASE_URL_PATCH_DEMO
import com.example.restapinews.Constant.BASE_URL_POST_DEMO
import com.example.restapinews.Constant.BASE_URL_PUT_Demo
import com.example.restapinews.databinding.ActivityPostResponseBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostResponse : AppCompatActivity() {
    private lateinit var binding: ActivityPostResponseBinding
    private lateinit var requestQueueGlobal: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            requestQueueGlobal = Volley.newRequestQueue(this@PostResponse)
            btnCancelRequest.setOnClickListener {
                requestQueueGlobal.cancelAll(CANCELLATION_TAG)
            }
            btnSendData.setOnClickListener {
                sendPostRequest(
                    edtTitle.text.toString(),
                    edtBody.text.toString(),
                    edtId.text.toString()
                )


                sendPutRequest(
                    "86",
                    edtTitle.text.toString(),
                    edtBody.text.toString(),
                    edtId.text.toString()
                )

                //   sendPatchRequest(edtTitle.text.toString())

                //      deleteRequest()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        requestQueueGlobal.cancelAll(CANCELLATION_TAG)
    }

    private fun sendPostRequest(title: String, body: String, userId: String) {

        val params = HashMap<String, String>()
        params["title"] = title
        params["body"] = body
        params["userId"] = userId

        val stringRequest = object : StringRequest(
            Method.POST,
            BASE_URL_POST_DEMO,
            {
                // handle your success
                Log.i("tag", "${it.toString()}")
                val token = object : TypeToken<PostData>() {}
                val response = Gson().fromJson(it, token)
                binding.txtResult.text = response.title
            },
            {
                //handle your failure
                Log.i("tag", "${it.toString()}")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }

            override fun cancel() {
                super.cancel()
                Log.i("tag", "Once request got cancel this block will execute")
            }

            override fun getPriority(): Priority {
                return Priority.IMMEDIATE
            }
        }

        stringRequest.tag = CANCELLATION_TAG
        requestQueueGlobal.add(stringRequest)
    }

    private fun sendPutRequest(id: String, title: String, body: String, userId: String) {
        val params = HashMap<String, String>()
        params["id"] = id
        params["title"] = title
        params["body"] = body
        params["userId"] = userId

        val stringRequest = object : StringRequest(
            Method.PUT,
            BASE_URL_PUT_Demo,
            {
                //success block
                Log.i("tag", "${it.toString()}")
                val token = object : TypeToken<PostData>() {}
                val response = Gson().fromJson(it, token)
                binding.txtResult.text = response.title
            }, {
                //error block
                Log.i("tag", "${it.toString()}")
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                return params
            }

            override fun cancel() {
                super.cancel()
                Log.i("tag", "Once request got cancel this block will execute 2")
            }
        }

        stringRequest.tag = CANCELLATION_TAG
        requestQueueGlobal.add(stringRequest)
    }

    private fun sendPatchRequest(title: String) {
        val requestQueue = Volley.newRequestQueue(this)
        val params = HashMap<String, String>()
        params["title"] = title

        val stringRequest = object : StringRequest(
            Method.PATCH,
            BASE_URL_PATCH_DEMO,
            {
                //success block
                Log.i("tag", "${it.toString()}")
                val token = object : TypeToken<PostData>() {}
                val response = Gson().fromJson(it, token)
                binding.txtResult.text = response.title
            }, {
                //error block
                Log.i("tag", "${it.toString()}")
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun deleteRequest() {
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Method.DELETE,
            BASE_URL_DELETE,
            {
                Log.i("tag", "${it.toString()}")
            },
            {
                Log.i("tag", "${it.toString()}")
            }
        )
        requestQueue.add(stringRequest)
    }

    companion object {
        const val CANCELLATION_TAG = "CANCELLATION_TAG"
    }
}