package com.prisyazhnuy.pockemonapp.network

data class PockemonItemBean(val height: Int,
                        val name: String,
                        val order: Int,
                        val weight: Int,
                        val sprites: Sprites)

data class Sprites(val front_default: String)