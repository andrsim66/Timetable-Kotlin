package com.sevenander.timetable.addEditLesson

import com.sevenander.timetable.data.cache.LessonCache
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.App

/**
 * Created by andrii on 6/1/17.
 */
class AddEditPresenter(val view: AddEditView) {

    var cache = LessonCache(App.instance.getDatabase())

    fun init() {

    }

    fun addLesson(name: String) {
        val lesson = LessonEntity(name, "teacher", LessonDay.MONDAY)
        cache.addLessonEntity(lesson)
    }
}