package com.prisyazhnuy.pockemonapp

import android.graphics.Bitmap
import android.util.LruCache
import com.prisyazhnuy.pockemonapp.provider.PockemonProviderImpl
import io.reactivex.Flowable

object ImageCache {
    private val lruCache = LruCache<String, Bitmap>(1024)

    fun get(url: String): Flowable<Bitmap> {
        return lruCache.get(url)?.let { Flowable.just(it) } ?: PockemonProviderImpl().loadImage(url)
                .doOnNext { lruCache.put(url, it) }
    }
}