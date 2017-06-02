package com.sevenander.kotlintest.data.cache.db

import android.database.Cursor
import com.sevenander.timetable.util.Logger
import java.util.*

/**
 * Created by andrii on 5/31/17.
 */
abstract class DbSelector<T> {

    fun getDbRecord(cursor: Cursor?): T? {
        var record: T? = null
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    record = parseCursor(cursor)
                } else {
                    Logger.e("can't move to first")
                }
                cursor.close()
            } else {
                Logger.e("Cursor is null")
            }
        } catch (e: Exception) {
            Logger.e(e.message, e)
        }

        return record
    }

    fun getDbRecords(cursor: Cursor?): List<T> {
        val list = ArrayList<T>()
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val t = parseCursor(cursor)
                        if (t != null)
                            list.add(t)
                    } while (cursor.moveToNext())
                } else {
                    Logger.e("can't move to first")
                }
                cursor.close()
            } else {
                Logger.e("Cursor is null")
            }
        } catch (e: Exception) {
            Logger.e(e.message, e)
        }

        return list
    }

    abstract fun parseCursor(cursor: Cursor): T?
}