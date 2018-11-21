package com.prisyazhnuy.pockemonapp.network

import android.graphics.Bitmap
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import android.graphics.BitmapFactory
import java.io.IOException
import java.io.InputStream
import java.net.URL


interface PockemonModule {
    fun getPockemons(): Flowable<List<Pockemon>>
    fun getPockemon(name: String): Flowable<PockemonItem>
    fun loadImage(path: String): Flowable<Bitmap>
}

class PockemonModuleImpl : PockemonModule {
    companion object {
        const val API_ENDPOINT = "https://pokeapi.co/api/v2/"
        const val POCKEMON_LIST = "pokemon/"
    }

    override fun getPockemons(): Flowable<List<Pockemon>> {
        return Flowable.create({ emitter: FlowableEmitter<List<Pockemon>> ->
            "$API_ENDPOINT$POCKEMON_LIST".httpGet().responseObject<PockemonListBean> { _, _, result ->
                result.component1()?.let {
                    emitter.onNext(it.results)
                    emitter.onComplete()
                } ?: result.component2()?.let { emitter.onError(it) }
            }
        }, BackpressureStrategy.BUFFER)
    }

    override fun getPockemon(name: String): Flowable<PockemonItem> {
        return Flowable.create({ emitter: FlowableEmitter<PockemonItem> ->
            "$API_ENDPOINT$POCKEMON_LIST$name".httpGet().responseJson() { request, response, result ->
                result.component1()?.let {
                    emitter.onNext(Gson().fromJson(it.content, PockemonItem::class.java))
                    emitter.onComplete()
                } ?: result.component2()?.let { emitter.onError(it) }
            }
        }, BackpressureStrategy.BUFFER)
    }

    override fun loadImage(path: String): Flowable<Bitmap> {
        return Flowable.create({ emitter: FlowableEmitter<Bitmap> ->
            try {
                val url = URL(path)
                emitter.onNext(BitmapFactory.decodeStream(url.content as InputStream))
                emitter.onComplete()
            } catch (e: IOException) {
                emitter.onError(e)
            }

        }, BackpressureStrategy.BUFFER)
    }
}