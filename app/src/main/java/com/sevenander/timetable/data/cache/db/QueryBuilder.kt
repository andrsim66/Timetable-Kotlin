package com.sevenander.timetable.data.cache.db

import com.sevenander.kotlintest.data.cache.db.DbContract
import com.sevenander.timetable.data.model.LessonDay


/**
 * Created by andrii on 5/31/17.
 */
class QueryBuilder {
    fun buildCreateTableLessonQuery(): String {
        return "CREATE TABLE " +
                DbContract.LessonEntry.TABLE_NAME + "(" +
                DbContract.LessonEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                DbContract.LessonEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DbContract.LessonEntry.COLUMN_TEACHER + " TEXT NOT NULL, " +
                DbContract.LessonEntry.COLUMN_DAY + " TEXT NOT NULL, " +
                DbContract.LessonEntry.COLUMN_START_TIME + " TEXT NOT NULL, " +
                DbContract.LessonEntry.COLUMN_END_TIME + " TEXT NOT NULL, " +

                "UNIQUE (" +
                DbContract.LessonEntry.COLUMN_ID +
                ") ON CONFLICT REPLACE);"
    }

    fun buildDeleteQuery(tableName: String): String {
        return "DROP TABLE IF EXISTS $tableName;"
    }

    fun buildLessonsQuery(day: LessonDay): String {
        return "SELECT *" +
                " FROM " +
                DbContract.LessonEntry.TABLE_NAME +
                " WHERE " +
                DbContract.LessonEntry.COLUMN_DAY + " = '${day.name}'" +
                " ORDER BY " +
                DbContract.LessonEntry.COLUMN_START_TIME +
                " ASC" +
                ";"
    }

    fun buildLessonQuery(id: Int): String {
        return "SELECT *" +
                " FROM " +
                DbContract.LessonEntry.TABLE_NAME +
                " WHERE " +
                DbContract.LessonEntry.COLUMN_ID + " = '$id'" +
                ";"
    }

    fun buildUpdateLessonQuery(lessonId: Int, name: String, teacher: String, day: String,
                               start: String, end: String): String {
        return "UPDATE " +
                DbContract.LessonEntry.TABLE_NAME +
                " SET " +
                DbContract.LessonEntry.COLUMN_NAME + " = '$name'," +
                DbContract.LessonEntry.COLUMN_TEACHER + " = '$teacher'," +
                DbContract.LessonEntry.COLUMN_DAY + " = '$day'," +
                DbContract.LessonEntry.COLUMN_START_TIME + " = '$start'," +
                DbContract.LessonEntry.COLUMN_END_TIME + " = '$end'" +
                " WHERE " +
                DbContract.LessonEntry.COLUMN_ID + " = '$lessonId'" +
                ";"
    }
}