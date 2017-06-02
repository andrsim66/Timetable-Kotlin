package com.sevenander.timetable.addEditLesson

import com.sevenander.timetable.DataView
import com.sevenander.timetable.data.model.LessonEntity

/**
 * Created by andrii on 6/1/17.
 */
interface AddEditView : DataView {
    fun showLesson(lesson: LessonEntity)

    fun hideLesson()

    fun setStartTime(startTime: String)

    fun setEndTime(endTime: String)

    fun showTimePickerDialog()

    fun hideTimePickerDialog()

    fun onLessonSaved()
}