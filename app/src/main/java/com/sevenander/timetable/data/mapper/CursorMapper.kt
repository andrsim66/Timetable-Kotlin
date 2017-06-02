package com.sevenander.kotlintest.data.mapper

import android.database.Cursor
import com.sevenander.kotlintest.data.cache.db.DbContract
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity

/**
 * Created by andrii on 5/31/17.
 */
class CursorMapper {
    fun getLessonEntity(cursor: Cursor): LessonEntity {

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.LessonEntry.COLUMN_ID))

        val name = cursor.getString(cursor.getColumnIndexOrThrow(
                DbContract.LessonEntry.COLUMN_NAME))
        val teacher = cursor.getString(cursor.getColumnIndexOrThrow(
                DbContract.LessonEntry.COLUMN_TEACHER))
        val day = LessonDay.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(
                DbContract.LessonEntry.COLUMN_DAY)))

        val entity = LessonEntity(name, teacher, day)
        entity.id = id

        entity.startTime = cursor.getString(cursor.getColumnIndexOrThrow(
                DbContract.LessonEntry.COLUMN_START_TIME))
        entity.endTime = cursor.getString(cursor.getColumnIndexOrThrow(
                DbContract.LessonEntry.COLUMN_END_TIME))

        return entity
    }
}