package com.prisyazhnuy.pockemonapp.provider

import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.model.PockemonModel
import com.prisyazhnuy.pockemonapp.network.PockemonModuleImpl
import io.reactivex.Flowable

interface PockemonProvider {
    fun getPockemons(): Flowable<List<Pockemon>>
}

class PockemonProviderImpl : PockemonProvider {

    private val pockemonModule by lazy { PockemonModuleImpl() }

    override fun getPockemons(): Flowable<List<Pockemon>> = pockemonModule.getPockemons()
            .flatMap {
                Flowable.fromIterable(it.results)
                        .map { PockemonModel(it.id.toLong(), it.name) }
                        .toList()
                        .toFlowable()
            }

}