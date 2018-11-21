package com.prisyazhnuy.pockemonapp.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

interface Pockemon {
    var id: Long?
    var name: String?
    var url: String?
}

class PockemonModel(override var id: Long? = null,
                    override var name: String? = null,
                    override var url: String? = null) : Pockemon {
    class Deserializer : ResponseDeserializable<Array<Pockemon>> {
        override fun deserialize(content: String): Array<Pockemon> = Gson().fromJson(content, Array<Pockemon>::class.java)
    }
}