package com.prisyazhnuy.pockemonapp.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.prisyazhnuy.pockemonapp.PockApp
import com.prisyazhnuy.pockemonapp.model.Pockemon
import com.prisyazhnuy.pockemonapp.model.PockemonItem
import com.prisyazhnuy.pockemonapp.model.PockemonModel
import io.reactivex.Flowable
import io.reactivex.Single
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

interface PockemonRepository {
    fun savePockemonList(pockemons: List<Pockemon>): Single<List<Pockemon>>
    fun getPockemonList(): Single<List<Pockemon>>

    fun savePockemonItem(item: PockemonItem): Single<PockemonItem>
    fun getPockemonItem(name: String): Single<PockemonItem>
}

class PockemonRepositoryImpl : PockemonRepository {

    override fun savePockemonList(pockemons: List<Pockemon>) =
            Flowable.fromIterable(pockemons)
                    .map {
                        PockApp.instance.database.use {
                            val values = ContentValues()
                            values.put(DatabaseOpenHelper.NAME, it.name)
                            values.put(DatabaseOpenHelper.URL, it.url)

                            insertWithOnConflict(
                                    DatabaseOpenHelper.POCKEMON_LIST,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_IGNORE
                            )
                        }
                    }
                    .toList()
                    .map { pockemons }

    override fun getPockemonList(): Single<List<Pockemon>> =
            Single.just(Unit)
                    .map {
                        PockApp.instance.database.use {
                            select(DatabaseOpenHelper.POCKEMON_LIST).parseList(classParser<PockemonModel>())
                        }
                    }

    override fun savePockemonItem(item: PockemonItem): Single<PockemonItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPockemonItem(name: String): Single<PockemonItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}