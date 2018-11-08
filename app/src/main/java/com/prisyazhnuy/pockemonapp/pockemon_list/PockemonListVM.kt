package com.prisyazhnuy.pockemonapp.pockemon_list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.prisyazhnuy.pockemonapp.model.NamedApiResourceList
import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.provider.PockemonProviderImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PockemonListVM : ViewModel() {

    val refreshLiveData = MutableLiveData<Boolean>()
    val pockemonsLD = MutableLiveData<List<Pockemon>>()
    val errorLD = MutableLiveData<Throwable>()

    private val pockemonProvider by lazy { PockemonProviderImpl() }

    fun loadInit() {
        refreshLiveData.value = true
        pockemonProvider.getPockemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<List<Pockemon>> { pockemonsLD.value = it },
                        Consumer<Throwable> { errorLD.value = it })
    }
}
