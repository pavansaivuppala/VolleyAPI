package com.example.restapinews

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.example.restapinews.R
import com.example.restapinews.databinding.ActivityImageCachingBinding
import com.example.restapinews.util.VolleyImageCaching

class ImageCachingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageCachingBinding

    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageCachingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCaching()
        initViews()
    }

    private fun initCaching() {
        VolleyImageCaching.initialize(this)
        imageLoader = VolleyImageCaching.imageLoader
    }

    private fun initViews() {
        with(binding) {
            btnSendImageReq.setOnClickListener {
                fetchImageUsingVolley(
                    URL,
                    binding.imgVolleyImageview,
                    R.drawable.ic_launcher_background,
                    R.drawable.baseline_error_24
                )
            }
        }
    }

    private fun fetchImageUsingVolley(
        url: String,
        imgVolleyImageview: NetworkImageView,
        @DrawableRes placeHolder: Int,
        @DrawableRes errorDrawable: Int
    ) {
        imageLoader.get(
            url,
            ImageLoader.getImageListener(
                imgVolleyImageview,
                placeHolder,
                errorDrawable
            )
        )

        binding.imgVolleyImageview.setImageUrl(url, imageLoader)
    }

    private companion object {
        const val URL = "https://images.dog.ceo/breeds/basenji/n02110806_4472.jpg"
    }
}