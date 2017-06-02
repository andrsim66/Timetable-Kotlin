package com.sevenander.timetable.addEditLesson

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.sevenander.timetable.R
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.util.Const
import java.util.*

class AddEditActivity : AppCompatActivity(), AddEditView {

    @BindView(R.id.et_lesson_name) lateinit var etLessonName: EditText
    @BindView(R.id.et_lesson_teacher) lateinit var etLessonTeacher: EditText
    @BindView(R.id.tv_lesson_day) lateinit var tvLessonDay: TextView
    @BindView(R.id.tv_lesson_start) lateinit var tvLessonStart: TextView
    @BindView(R.id.tv_lesson_end) lateinit var tvLessonEnd: TextView

    var timePicker: TimePickerDialog? = null

    var editMode: Boolean = false
    var lessonId: Int = -1
    var dayNumber: Int = -1

    var presenter: AddEditPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        ButterKnife.bind(this@AddEditActivity)

        init()
    }

    override fun onDestroy() {
        presenter?.destroy()
        super.onDestroy()
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
        dayNumber = lesson.day.ordinal
        etLessonName.setText(lesson.name)
        etLessonTeacher.setText(lesson.teacher)
        tvLessonDay.text = lesson.day.name.toLowerCase().capitalize()
        tvLessonStart.text = lesson.startTime
        tvLessonEnd.text = lesson.endTime
    }

    override fun hideLesson() {

    }

    override fun showTimePickerDialog() {
        if (timePicker == null || !(timePicker as TimePickerDialog).isShowing) {
            val calendar = Calendar.getInstance()
            timePicker = TimePickerDialog(context(), presenter, calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true)
            timePicker?.show()
        }
    }

    override fun hideTimePickerDialog() {
        if (timePicker != null && (timePicker as TimePickerDialog).isShowing) {
            timePicker?.dismiss()
        }
    }

    override fun setStartTime(startTime: String) {
        tvLessonStart.text = startTime
    }

    override fun setEndTime(endTime: String) {
        tvLessonEnd.text = endTime
    }

    override fun onLessonSaved() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun context(): Context {
        return this@AddEditActivity
    }

    @OnClick(R.id.tv_lesson_start)
    fun onTimeStartClick() {
        presenter?.pickTimeClick(true)
    }

    @OnClick(R.id.tv_lesson_end)
    fun onTimeEndClick() {
        presenter?.pickTimeClick(false)
    }

    @OnClick(R.id.b_save)
    fun onSaveClick() {
        presenter?.saveClicked(lessonId, etLessonName.text.toString(),
                etLessonTeacher.text.toString(), dayNumber,
                tvLessonStart.text.toString(),
                tvLessonEnd.text.toString())
    }

    private fun init() {
        getExtras()

        presenter = AddEditPresenter(this@AddEditActivity)
        if (editMode)
            presenter?.init(lessonId)
    }

    private fun getExtras() {
        editMode = intent.extras.containsKey(Const.KEY_LESSON_ID)
        if (editMode) {
            lessonId = intent.extras.getInt(Const.KEY_LESSON_ID)
        } else {
            dayNumber = intent.extras.getInt(Const.KEY_LESSON_DAY)
        }
    }
}
