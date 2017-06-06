package com.sevenander.timetable.viewTimetable

import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.prefs.PrefsManager

/**
 * Created by andrii on 6/6/17.
 */
class MainPresenter(private var view: MainView) {

    private var prefs = PrefsManager(view.context())

    fun init() {
        loadDaySettings()
    }

    private fun loadDaySettings() {
        val prefDays = prefs.getSelectedDays()
        val filtered = LessonDay.values().filter { prefDays.contains(it.name) }
        val days = Array<String?>(filtered.size, { filtered[it].name.orEmpty() })
        view.onTitlesReceive(days)
    }
}