package com.sevenander.timetable.addEditLesson

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.R

class AddEditActivity : AppCompatActivity(), AddEditView {
    @BindView(R.id.et_lesson_name) lateinit var etLessonName: EditText

    var presenter: AddEditPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        ButterKnife.bind(this@AddEditActivity)

        init()
    }

    override fun hideProgress() {

    }

    override fun showError(message: String) {

    }

    override fun hideError() {

    }

    override fun showLesson(lesson: LessonEntity) {

    }

    override fun context(): Context {
        return this@AddEditActivity
    }

    override fun showProgress() {

    }

    @OnClick(R.id.b_save)
    fun onSaveClick() {
        presenter?.addLesson(etLessonName.text.toString())
    }

    fun init() {
        presenter = AddEditPresenter(this@AddEditActivity)
        presenter?.init()
    }
}
