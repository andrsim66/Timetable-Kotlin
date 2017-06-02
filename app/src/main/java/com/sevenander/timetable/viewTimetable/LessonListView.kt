package com.sevenander.timetable.viewTimetable

import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.DataView

/**
 * Created by andrii on 5/29/17.
 */
interface LessonListView : DataView {
    fun showList(lessons: List<LessonEntity>)

    fun hideList()
}