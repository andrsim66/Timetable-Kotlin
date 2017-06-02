package com.sevenander.timetable

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.sevenander.kotlintest.data.cache.db.DbHelper

/**
 * Created by andrii on 6/1/17.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private lateinit var dbHelper: DbHelper

    override fun onCreate() {
        super.onCreate()
        instance = this@App
        dbHelper = DbHelper(applicationContext)
    }

    @Synchronized fun getDatabase(): SQLiteDatabase {
        return dbHelper.writableDatabase
    }
}