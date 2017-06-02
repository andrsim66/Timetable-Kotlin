package com.sevenander.timetable.data.cache

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.sevenander.kotlintest.data.cache.db.DbContract
import com.sevenander.kotlintest.data.cache.db.DbSelector
import com.sevenander.kotlintest.data.mapper.CursorMapper
import com.sevenander.timetable.data.cache.db.QueryBuilder
import com.sevenander.timetable.data.mapper.ContentValuesMapper
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.util.Logger

/**
 * Created by andrii on 5/31/17.
 */
class LessonCache(private val database: SQLiteDatabase) {

    private val queryBuilder: QueryBuilder = QueryBuilder()
    private val cursorMapper: CursorMapper = CursorMapper()
    private val contentValuesMapper: ContentValuesMapper = ContentValuesMapper()

    fun getLessons(day: LessonDay): List<LessonEntity> {
        val selector = object : DbSelector<LessonEntity>() {
            override fun parseCursor(cursor: Cursor): LessonEntity {
                return cursorMapper.getLessonEntity(cursor)
            }
        }

        val cursor = selectLessonsByDay(day)

        return selector.getDbRecords(cursor)
    }

    fun getLesson(id: Int): LessonEntity? {
        val selector = object : DbSelector<LessonEntity>() {
            override fun parseCursor(cursor: Cursor): LessonEntity {
                return cursorMapper.getLessonEntity(cursor)
            }
        }

        val cursor = selectLessonById(id)

        return selector.getDbRecord(cursor)
    }

    private fun selectLessonsByDay(day: LessonDay): Cursor? {
        val query = queryBuilder.buildLessonsQuery(day)
        return execRawQuery(query)
    }

    private fun selectLessonById(id: Int): Cursor? {
        val query = queryBuilder.buildLessonQuery(id)
        return execRawQuery(query)
    }

    fun addLessonEntity(entity: LessonEntity) {
        addEntity(DbContract.LessonEntry.TABLE_NAME, entity)
    }

    fun updateLessonEntity(entity: LessonEntity) {
        updateEntity(DbContract.LessonEntry.TABLE_NAME, entity)
    }

    private fun addEntity(tableName: String, entity: LessonEntity) {
        database.beginTransaction()
        try {
            insert(tableName, entity)
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
    }

    private fun updateEntity(tableName: String, entity: LessonEntity) {
        val contentValues = fillContent(entity)

        database.update(tableName, contentValues,
                DbContract.LessonEntry.COLUMN_ID + " = '${entity.id}'", null)
    }

    private fun insert(tableName: String, entity: LessonEntity) {
        val contentValues = fillContent(entity)

        database.insertWithOnConflict(tableName, null, contentValues,
                SQLiteDatabase.CONFLICT_REPLACE)
    }

    private fun fillContent(entity: LessonEntity): ContentValues {
        return contentValuesMapper.fillContentValues(entity)
    }

    private fun execRawQuery(query: String): Cursor? {
        var cursor: Cursor? = null
        database.beginTransaction()
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: Exception) {
            Logger.e(e.message, e)
        } finally {
            database.endTransaction()
        }
        return cursor
    }

    private fun execSqlQuery(query: String) {
        database.execSQL(query)
    }
}