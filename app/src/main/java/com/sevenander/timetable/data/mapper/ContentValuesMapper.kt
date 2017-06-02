package com.sevenander.timetable.data.mapper

import android.content.ContentValues
import com.sevenander.kotlintest.data.cache.db.DbContract
import com.sevenander.timetable.data.model.LessonEntity

/**
 * Created by andrii on 5/31/17.
 */
class ContentValuesMapper {

    fun fillContentValues(lesson: LessonEntity): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(DbContract.LessonEntry.COLUMN_NAME, lesson.name)
        contentValues.put(DbContract.LessonEntry.COLUMN_TEACHER, lesson.teacher)
        contentValues.put(DbContract.LessonEntry.COLUMN_DAY, lesson.day.name)
        contentValues.put(DbContract.LessonEntry.COLUMN_START_TIME, lesson.startTime)
        contentValues.put(DbContract.LessonEntry.COLUMN_END_TIME, lesson.endTime)

        return contentValues
    }
}