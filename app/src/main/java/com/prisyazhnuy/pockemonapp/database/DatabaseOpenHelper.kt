package com.prisyazhnuy.pockemonapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        const val POCKEMON_LIST = "PockemonList"
        const val POCKEMONS = "Pockemons"
        const val NAME = "name"
        const val URL = "url"
        const val HEIGHT = "height"
        const val ORDER = "order_value"
        const val WEIGHT = "weight"

        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(POCKEMON_LIST, true,
                NAME to TEXT + PRIMARY_KEY + UNIQUE,
                URL to TEXT)
        db.createTable(POCKEMONS, true,
                NAME to TEXT + PRIMARY_KEY + UNIQUE,
                HEIGHT to INTEGER,
                ORDER to INTEGER,
                WEIGHT to INTEGER,
                URL to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("PockemonList", true)
        db.dropTable("Pockemons", true)
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)