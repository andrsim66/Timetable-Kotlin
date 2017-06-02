package com.sevenander.timetable.data.prefs

import android.content.Context
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.util.Const

/**
 * Created by andrii on 6/2/17.
 */
class PrefsManager(var context: Context) {

    private val prefs = context.getSharedPreferences(Const.KEY_PREFS, Context.MODE_PRIVATE)

    fun saveSelectedDays(days: Set<String>) {
        val editor = prefs.edit()

        editor.putStringSet(Const.KEY_PREFS_DAYS, days)
        editor.apply()
    }

    fun getSelectedDays(): Set<String> {
        val defaultSet = LessonDay.values()
                .map { it.name }
                .toSet()

        return HashSet<String>(prefs.getStringSet(Const.KEY_PREFS_DAYS, defaultSet))
    }
}