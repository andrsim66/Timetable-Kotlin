package com.sevenander.timetable.addEditLesson

import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.DataView

/**
 * Created by andrii on 6/1/17.
 */
interface AddEditView : DataView {
    fun showLesson(lesson: LessonEntity)
}