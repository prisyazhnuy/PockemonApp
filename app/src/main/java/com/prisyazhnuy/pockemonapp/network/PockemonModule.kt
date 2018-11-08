package com.prisyazhnuy.pockemonapp.network

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.prisyazhnuy.pockemonapp.model.NamedApiResourceList
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter

interface PockemonModule {
    fun getPockemons(): Flowable<NamedApiResourceList>
}

class PockemonModuleImpl : PockemonModule {
    companion object {
        const val API_ENDPOINT = "https://pokeapi.co/api/v2/"
        const val POCKEMON_LIST = "pokemon/"
    }

    override fun getPockemons(): Flowable<NamedApiResourceList> {
        return Flowable.create({ emitter: FlowableEmitter<NamedApiResourceList> ->
            "$API_ENDPOINT$POCKEMON_LIST".httpGet().responseObject<NamedApiResourceList> { _, _, result ->
                result.component1()?.let {
                    emitter.onNext(it)
                    emitter.onComplete()
                } ?: result.component2()?.let { emitter.onError(it) }
            }
        }, BackpressureStrategy.BUFFER)
    }
}