package com.sevenander.timetable.viewTimetable

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.sevenander.timetable.util.Navigator
import com.sevenander.timetable.viewTimetable.adapter.LessonPagerAdapter
import com.sevenander.timetable.R

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tl_headers) lateinit var tlHeaders: TabLayout
    @BindView(R.id.vp_timetable) lateinit var vpTimetable: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this@MainActivity)

        init()
    }

    @OnClick(R.id.fab_add)
    fun onAddClick() {
        Navigator.startAddActivity(this@MainActivity, vpTimetable.currentItem)
    }

    private fun init() {
        val adapter = LessonPagerAdapter(supportFragmentManager)
        vpTimetable.adapter = adapter
        tlHeaders.setupWithViewPager(vpTimetable)
    }
}
