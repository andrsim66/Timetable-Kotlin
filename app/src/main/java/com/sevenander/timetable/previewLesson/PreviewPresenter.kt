package com.sevenander.timetable.previewLesson

import com.sevenander.timetable.data.cache.LessonCache
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.App
import com.sevenander.timetable.R

/**
 * Created by andrii on 6/2/17.
 */
class PreviewPresenter(val view: PreviewView) {

    var cache = LessonCache(App.instance.getDatabase())

    fun init(id: Int) {
        loadData(id)
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
}