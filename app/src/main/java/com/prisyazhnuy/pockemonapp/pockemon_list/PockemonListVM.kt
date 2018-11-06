package com.prisyazhnuy.pockemonapp.pockemon_list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.prisyazhnuy.pockemonapp.FIRST_POSITION

class PockemonListVM : ViewModel() {

    val refreshLiveData = MutableLiveData<Boolean>()

    fun loadInit() {
        refreshLiveData.value = true
        loadData(FIRST_POSITION)
                .compose(RxUtils.ioToMainTransformer())
                .subscribe(initConsumer, onErrorConsumer)
    }

    fun loadMore(offset: Int) {
        refreshLiveData.value = true
        loadData(offset)
                .compose(RxUtils.ioToMainTransformer())
                .subscribe(loadMoreConsumer, onErrorConsumer)
    }
}
