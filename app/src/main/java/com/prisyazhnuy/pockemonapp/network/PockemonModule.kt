package com.prisyazhnuy.pockemonapp.network

import com.github.kittinunf.fuel.httpGet
import com.prisyazhnuy.pockemonapp.model.Pockemon

interface PockemonModule {
    fun getPockemons(): List<Pockemon>
}

class PockemonModuleImpl : PockemonModule {
    companion object {
        const val API_ENDPOINT = "https://pokeapi.co/api/v2/"
        const val POCKEMON_LIST = "pokemon/"
    }

    override fun getPockemons(): List<Pockemon> {
        "$API_ENDPOINT$POCKEMON_LIST".httpGet().response {
            request, response, result ->
            //response handling
        }
    }
}