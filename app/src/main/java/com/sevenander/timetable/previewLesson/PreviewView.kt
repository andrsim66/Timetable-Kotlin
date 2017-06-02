package com.sevenander.timetable.previewLesson

import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.DataView

/**
 * Created by andrii on 6/2/17.
 */
interface PreviewView : DataView {
    fun showLesson(lesson: LessonEntity)

    fun hideLesson()
}