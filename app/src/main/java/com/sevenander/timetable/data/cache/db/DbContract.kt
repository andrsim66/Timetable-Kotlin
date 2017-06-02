package com.sevenander.kotlintest.data.cache.db

/**
 * Created by andrii on 5/31/17.
 */
class DbContract {

    class LessonEntry {
        companion object {
            val TABLE_NAME = "lessons"

            val COLUMN_ID = "_id"
            val COLUMN_NAME = "name"
            val COLUMN_TEACHER = "teacher"
            val COLUMN_DAY = "day"
            val COLUMN_START_TIME = "startTime"
            val COLUMN_END_TIME = "endTime"
        }
    }
}