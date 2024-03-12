package com.example.restapinews

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restapinews.Constant.BASE_URL_DELETE
import com.example.restapinews.Constant.BASE_URL_PATCH_DEMO
import com.example.restapinews.Constant.BASE_URL_POST_DEMO
import com.example.restapinews.Constant.BASE_URL_PUT_Demo
import com.example.restapinews.Constant.BASE_URL_RANDOM_DOG

class VolleyHandler(private val context: Context) {

    fun sendGetTypeRequest() {
        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = object : StringRequest(
            Method.GET,
            BASE_URL_RANDOM_DOG,
            {
                //Success block
            }, {
                // error block
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["API_KEY"] = "value of the API key"
                return super.getHeaders()
            }
        }
        requestQueue.add(stringRequest)
    }

    fun sendPostRequest(title: String, body: String, userId: String) {
        val requestQueue = Volley.newRequestQueue(context)

        val params = HashMap<String, String>()
        params["title"] = title
        params["body"] = body
        params["userId"] = userId

        val stringRequest = object : StringRequest(
            Method.POST,
            BASE_URL_POST_DEMO,
            {
                // handle your success
            },
            {
                //handle your failure
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun sendPutRequest(id: String, title: String, body: String, userId: String) {
        val requestQueue = Volley.newRequestQueue(context)
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
            }, {
                //error block
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun retryMechanismUsingVolley(){
        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET,
            BASE_URL_RANDOM_DOG,
            {
                //Success block
            }, {
                // error block
            }
        )

        //Case 1 where timeout = 25 sec, no of try = 1 and backoff = 1f
        val defaultRetryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        stringRequest.retryPolicy = defaultRetryPolicy

        //Case 2 where timeout = 30sec, no of try = 3, backoff = 2f
        val customRetryPolicy = DefaultRetryPolicy(
            3000,
            3,
            2f
        )
        stringRequest.retryPolicy = customRetryPolicy

        requestQueue.add(stringRequest)
    }

    private fun sendPatchRequest(id: String, title: String, body: String, userId: String) {
        val requestQueue = Volley.newRequestQueue(context)
        val params = HashMap<String, String>()
        params["title"] = title

        val stringRequest = object : StringRequest(
            Method.PATCH,
            BASE_URL_PATCH_DEMO,
            {
                //success block
            }, {
                //error block
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }

        requestQueue.add(stringRequest)
    }
}