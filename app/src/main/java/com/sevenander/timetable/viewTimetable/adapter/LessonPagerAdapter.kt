package com.sevenander.timetable.viewTimetable.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.sevenander.timetable.viewTimetable.LessonListFragment

/**
 * Created by andrii on 6/1/17.
 */
class LessonPagerAdapter(fragmentManager: FragmentManager, private var titles: Array<String?>) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LessonListFragment.newInstance(getPageTitle(position).toString())
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position].orEmpty()
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }
}