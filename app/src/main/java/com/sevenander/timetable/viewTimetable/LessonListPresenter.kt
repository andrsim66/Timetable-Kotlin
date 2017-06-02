package com.sevenander.timetable.viewTimetable

import com.sevenander.timetable.data.cache.LessonCache
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.App
import com.sevenander.timetable.R

/**
 * Created by andrii on 5/29/17.
 */
class LessonListPresenter constructor(val view: LessonListView) {

    private var cache = LessonCache(App.instance.getDatabase())

    fun init(lessonDay: LessonDay) {
        loadData(lessonDay)
    }

    private fun loadData(lessonDay: LessonDay) {
        view.hideList()
        view.hideError()
        view.showProgress()

        val data = cache.getLessons(lessonDay)
        dataReceived(data)
    }

    private fun dataReceived(data: List<LessonEntity>) {
        if (data.isEmpty()) {
            view.showError(view.context().getString(R.string.no_data_to_show))
            view.hideList()
        } else {
            view.showList(data)
            view.hideError()
        }
        view.hideProgress()
    }
}