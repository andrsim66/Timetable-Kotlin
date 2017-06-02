package com.sevenander.timetable.settings

import android.content.Context

/**
 * Created by andrii on 6/2/17.
 */
interface SettingsView {
    fun showDaySettings(items: Array<String?>, checkedItems: BooleanArray)

    fun context(): Context
}