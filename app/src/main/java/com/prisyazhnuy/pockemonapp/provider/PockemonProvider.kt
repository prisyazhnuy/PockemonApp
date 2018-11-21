package com.prisyazhnuy.pockemonapp.provider

import android.graphics.Bitmap
import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import com.prisyazhnuy.pockemonapp.network.PockemonModuleImpl
import io.reactivex.Flowable

interface PockemonProvider {
    fun getPockemons(): Flowable<List<Pockemon>>
    fun getPockemon(name: String): Flowable<PockemonItem>
    fun loadImage(path: String): Flowable<Bitmap>
}

class PockemonProviderImpl : PockemonProvider {

    private val pockemonModule by lazy { PockemonModuleImpl() }

    override fun getPockemons(): Flowable<List<Pockemon>> = pockemonModule.getPockemons()

    override fun getPockemon(name: String) = pockemonModule.getPockemon(name)

    override fun loadImage(path: String) = pockemonModule.loadImage(path)
}