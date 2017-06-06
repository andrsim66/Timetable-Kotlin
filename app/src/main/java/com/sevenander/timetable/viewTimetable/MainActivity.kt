package com.sevenander.timetable.viewTimetable

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.sevenander.timetable.R
import com.sevenander.timetable.util.Const
import com.sevenander.timetable.util.Navigator
import com.sevenander.timetable.viewTimetable.adapter.LessonPagerAdapter

class MainActivity : AppCompatActivity(), MainView {

    @BindView(R.id.tl_headers) lateinit var tlHeaders: TabLayout
    @BindView(R.id.vp_timetable) lateinit var vpTimetable: ViewPager

    private lateinit var adapter: LessonPagerAdapter
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this@MainActivity)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.menu_action_settings) {
            Navigator.startSettingsActivity(this@MainActivity)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Const.REQUEST_SETTINGS -> presenter.init()
                Const.REQUEST_ADD_EDIT -> adapter.notifyDataSetChanged()
            }
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(message: String) {

    }

    override fun hideError() {

    }

    override fun context(): Context {
        return this@MainActivity
    }

    override fun onTitlesReceive(titles: Array<String?>) {
        adapter = LessonPagerAdapter(supportFragmentManager, titles)
        vpTimetable.adapter = adapter
        tlHeaders.setupWithViewPager(vpTimetable)
    }

    @OnClick(R.id.fab_add)
    fun onAddClick() {
        Navigator.startAddActivity(this@MainActivity, vpTimetable.currentItem)
    }

    private fun init() {
        presenter = MainPresenter(this)
        presenter.init()
    }
}
