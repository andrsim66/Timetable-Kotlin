package com.sevenander.timetable.previewLesson

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.R
import com.sevenander.timetable.util.Const

class PreviewActivity : AppCompatActivity(), PreviewView {

    @BindView(R.id.tv_lesson_name) lateinit var tvLessonName: TextView
    @BindView(R.id.tv_lesson_day) lateinit var tvLessonDay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        ButterKnife.bind(this@PreviewActivity)

        init()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(message: String) {

    }

    override fun hideError() {

    }

    override fun showLesson(lesson: LessonEntity) {
        tvLessonName.text = lesson.name
        tvLessonDay.text = lesson.day.name.toLowerCase().capitalize()
    }

    override fun context(): Context {
        return this@PreviewActivity
    }

    override fun hideLesson() {

    }

    private fun init() {
        val id = intent.extras.getInt(Const.KEY_LESSON_ID)
        val presenter = PreviewPresenter(this@PreviewActivity)
        presenter.init(id)
    }

    private fun setupViews() {

    }
}
