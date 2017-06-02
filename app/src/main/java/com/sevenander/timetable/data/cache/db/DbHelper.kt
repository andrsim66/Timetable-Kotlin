package com.sevenander.kotlintest.data.cache.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sevenander.timetable.data.cache.db.QueryBuilder

/**
 * Created by andrii on 5/31/17.
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    val queryBuilder: QueryBuilder = QueryBuilder()

    companion object {
        private val DB_VERSION: Int = 1
        private val DB_NAME: String = "timetable.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(queryBuilder.buildCreateTableLessonQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(queryBuilder.buildDeleteQuery(DbContract.LessonEntry.TABLE_NAME))
        onCreate(db)
    }
}