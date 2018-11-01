package com.prisyazhnuy.pockemonapp.model

interface Pockemon {
    var id: Long?
    var name: String?
    var image: String?
}

class PockemonModel(override var id: Long? = null,
                    override var name: String? = null,
                    override var image: String? = null) : Pockemon