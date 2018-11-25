package com.prisyazhnuy.pockemonapp.model

interface PockemonItem {
    var height: Int
    var name: String
    var order: Int
    var weight: Int
    var image: String
}

class PockemonItemModel(override var name: String,
                        override var height: Int,
                        override var order: Int,
                        override var weight: Int,
                        override var image: String) : PockemonItem