package com.example.restapinews.util

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader

object VolleyImageCaching {
    lateinit var imageLoader: ImageLoader
        private set

    fun initialize(context: Context) {
        RequestQueue(
            DiskBasedCache(context.cacheDir, 100000),
            BasicNetwork(HurlStack())
        ).apply {
            start()
            imageLoader = ImageLoader(
                this,
                object : ImageLoader.ImageCache {
                    private val internal_cache = LruCache<String, Bitmap>(15)
                    override fun getBitmap(url: String): Bitmap? = internal_cache[url]
                    override fun putBitmap(url: String?, bitmap: Bitmap?) {
                        internal_cache.put(url, bitmap)
                    }
                }
            )
        }
    }
}