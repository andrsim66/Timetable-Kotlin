package com.sevenander.timetable.settings

import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.prefs.PrefsManager

/**
 * Created by andrii on 6/2/17.
 */
class SettingsPresenter(private var view: SettingsView) {

    private var prefs = PrefsManager(view.context())
    private lateinit var days: Array<String?>

    fun loadSettings() {
        val prefDays = prefs.getSelectedDays()

        days = arrayOfNulls<String>(LessonDay.values().size)
        val checkedDays = BooleanArray(LessonDay.values().size)

        for (i in LessonDay.values().indices) {
            days[i] = LessonDay.values()[i].name
            checkedDays[i] = prefDays.contains(LessonDay.values()[i].name)
        }

        view.showDaySettings(days, checkedDays)
    }

    fun saveDays(checkedItems: BooleanArray) {
        val newDays = HashSet<String>()
        days.indices
                .filter { checkedItems[it] }
                .mapTo(newDays) { days[it].orEmpty() }
        prefs.saveSelectedDays(newDays)
    }
}