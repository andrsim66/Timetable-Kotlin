package com.sevenander.timetable.data.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by andrii on 5/31/17.
 */
data class LessonEntity(var name: String, var teacher: String, var day: LessonDay) {

    var id: Int = -1
    var startTime: String = "00:00"
    var endTime: String = "00:00"

    fun getStratTime(): Date {
        return SimpleDateFormat("HH.mm", Locale.ENGLISH).parse(startTime)
    }

    fun setStratTime(date: Date) {
        startTime = SimpleDateFormat("HH.mm", Locale.ENGLISH).format(date)
    }

    fun getEndTime(): Date {
        return SimpleDateFormat("HH.mm", Locale.ENGLISH).parse(endTime)
    }

    fun setEndTime(date: Date) {
        endTime = SimpleDateFormat("HH.mm", Locale.ENGLISH).format(date)
    }
}