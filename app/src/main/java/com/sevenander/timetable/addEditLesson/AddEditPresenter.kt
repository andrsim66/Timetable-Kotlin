package com.sevenander.timetable.addEditLesson

import android.app.TimePickerDialog
import android.widget.TimePicker
import com.sevenander.timetable.App
import com.sevenander.timetable.R
import com.sevenander.timetable.data.cache.LessonCache
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity

/**
 * Created by andrii on 6/1/17.
 */
class AddEditPresenter(val view: AddEditView) : TimePickerDialog.OnTimeSetListener {

    private var startTimePicking: Boolean = false

    private var cache = LessonCache(App.instance.getDatabase())

    override fun onTimeSet(picker: TimePicker?, hourOfDay: Int, minute: Int) {
        val time = "${"%02d".format(hourOfDay)}:${"%02d".format(minute)}"
        if (startTimePicking) {
            view.setStartTime(time)
        } else {
            view.setEndTime(time)
        }
    }

    fun init(lessonId: Int) {
        view.showProgress()
        loadData(lessonId)
    }

    fun destroy() {
        view.hideTimePickerDialog()
    }

    fun pickTimeClick(startTime: Boolean) {
        view.hideTimePickerDialog()
        startTimePicking = startTime
        view.showTimePickerDialog()
    }

    fun saveClicked(lessonId: Int, name: String, teacher: String, dayNumber: Int, start: String,
                    end: String) {
        if (!validate(name, teacher, dayNumber, start, end))
            return

        if (lessonId == -1) {
            addLesson(name, teacher, dayNumber, start, end)
        } else {
            updateLesson(lessonId, name, teacher, dayNumber, start, end)
        }
        view.onLessonSaved()
    }

    private fun addLesson(name: String, teacher: String, dayNumber: Int, start: String, end: String) {
        val lesson = LessonEntity(name, teacher, LessonDay.values()[dayNumber])
        lesson.startTime = start
        lesson.endTime = end
        cache.addLessonEntity(lesson)
    }

    private fun updateLesson(lessonId: Int, name: String, teacher: String, dayNumber: Int,
                             start: String, end: String) {
        val lesson = LessonEntity(name, teacher, LessonDay.values()[dayNumber])
        lesson.id = lessonId
        lesson.startTime = start
        lesson.endTime = end
        cache.updateLessonEntity(lesson)
    }

    private fun validate(name: String, teacher: String, dayNumber: Int, start: String, end: String)
            : Boolean {

        if (name.isNullOrBlank())
            return false

        if (teacher.isNullOrBlank())
            return false

        if (dayNumber == -1)
            return false

        if (start.isTimeNullOrBlank())
            return false

        if (end.isTimeNullOrBlank())
            return false

        return true
    }

    private fun loadData(id: Int) {
        view.hideError()
        view.showProgress()

        val data = cache.getLesson(id)
        dataReceived(data)
    }

    private fun dataReceived(data: LessonEntity?) {
        if (data == null) {
            view.showError(view.context().getString(R.string.no_data_to_show))
            view.hideLesson()
        } else {
            view.showLesson(data)
            view.hideError()
        }
        view.hideProgress()
    }

    private fun CharSequence?.isTimeNullOrBlank(): Boolean = this == null ||
            this.isBlank() || this == "00:00"
}