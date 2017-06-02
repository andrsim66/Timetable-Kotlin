package com.sevenander.timetable.viewTimetable.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.viewTimetable.LessonListFragment

/**
 * Created by andrii on 6/1/17.
 */
class LessonPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LessonListFragment.newInstance(LessonDay.values()[position].name)
    }

    override fun getCount(): Int {
        return LessonDay.values().size //todo get count from daynum prefs
    }

    override fun getPageTitle(position: Int): CharSequence {
        return LessonDay.values()[position].name
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }
}