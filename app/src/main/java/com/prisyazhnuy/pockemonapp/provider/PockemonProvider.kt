package com.prisyazhnuy.pockemonapp.provider

import android.graphics.Bitmap
import com.prisyazhnuy.pockemonapp.database.PockemonRepositoryImpl
import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import com.prisyazhnuy.pockemonapp.network.PockemonModuleImpl
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function

interface PockemonProvider {
    fun getPockemons(): Flowable<List<Pockemon>>
    fun getPockemon(name: String): Flowable<PockemonItem>
    fun loadImage(path: String): Flowable<Bitmap>
}

class PockemonProviderImpl : PockemonProvider {

    private val pockemonModule by lazy { PockemonModuleImpl() }
    private val pockemonRepository by lazy { PockemonRepositoryImpl() }

    override fun getPockemons(): Flowable<List<Pockemon>> =
            pockemonModule.getPockemons()
                    .flatMapSingle { pockemonRepository.savePockemonList(it) }
                    .onErrorResumeNext(Function<Throwable, Flowable<List<Pockemon>>> { cause ->
                        pockemonRepository.getPockemonList()
                                .onErrorResumeNext { Single.error(cause) }
                                .toFlowable()
                    })

    override fun getPockemon(name: String) =
            pockemonModule.getPockemon(name)
                    .flatMapSingle { pockemonRepository.savePockemonItem(it) }
                    .onErrorResumeNext(Function<Throwable, Flowable<PockemonItem>> { cause ->
                        pockemonRepository.getPockemonItem(name)
                                .onErrorResumeNext { Single.error(cause) }
                                .toFlowable()
                    })

    override fun loadImage(path: String) = pockemonModule.loadImage(path)
}