package com.sulistyolabs.made.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorited.db",
    null,2 ) {

    companion object {

        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(MovieDB.TABLE_FAVORITE_MOVIE, true,
            MovieDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MovieDB.MOVIE_ID to TEXT + UNIQUE,
            MovieDB.JSON_DATA to TEXT
        )

        db?.createTable(TvshowDB.TABLE_FAVORITE_TV, true,
            TvshowDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TvshowDB.TVSHOW_ID to TEXT + UNIQUE,
            TvshowDB.JSON_DATA to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(MovieDB.TABLE_FAVORITE_MOVIE, true)

    }
}

val  Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)