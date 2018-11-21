package com.prisyazhnuy.pockemonapp.network

import com.prisyazhnuy.pockemonapp.model.PockemonModel

data class PockemonListBean(val count: Int,
                            val results: List<PockemonModel>)