package com.prisyazhnuy.pockemonapp.pockemon_details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import android.util.Log
import com.prisyazhnuy.pockemonapp.ImageCache
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import com.prisyazhnuy.pockemonapp.provider.PockemonProviderImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PockemonDetailsVM : ViewModel() {
    val pockemonLD = MutableLiveData<PockemonItem>()
    val pockemonIcon = MutableLiveData<Bitmap>()

    private val pockemonProvider by lazy { PockemonProviderImpl() }

    fun loadPockemon(name: String?) {
        name?.let {
            pockemonProvider.getPockemon(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Consumer<PockemonItem> { pockemonLD.value = it }, Consumer { Log.e("PockemonDetailsVM", "error", it) })
        }
    }

    fun loadImage(path: String) {
        ImageCache.get(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<Bitmap> { pockemonIcon.value = it }, Consumer { Log.e("PockemonDetailsVM", "error", it) })
    }
}