package com.sevenander.timetable.viewTimetable

import com.sevenander.timetable.DataView

/**
 * Created by andrii on 6/6/17.
 */
interface MainView : DataView {
    fun onTitlesReceive(titles: Array<String?>)
}