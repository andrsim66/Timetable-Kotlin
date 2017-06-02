package com.sevenander.timetable.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.sevenander.timetable.addEditLesson.AddEditActivity
import com.sevenander.timetable.previewLesson.PreviewActivity
import com.sevenander.timetable.settings.SettingsActivity

/**
 * Created by andrii on 6/1/17.
 */
class Navigator {
    companion object {

        fun startAddActivity(activity: Activity, dayNumber: Int) {
            val intent = Intent(activity, AddEditActivity::class.java)
            intent.putExtra(Const.KEY_LESSON_DAY, dayNumber)
            activity.startActivityForResult(intent, Const.REQUEST_ADD_EDIT)
        }

        fun startEditActivity(activity: Activity, lessonId: Int) {
            val intent = Intent(activity, AddEditActivity::class.java)
            intent.putExtra(Const.KEY_LESSON_ID, lessonId)
            activity.startActivityForResult(intent, Const.REQUEST_ADD_EDIT)
        }

        fun startEditActivity(fragment: Fragment, lessonId: Int) {
            val intent = Intent(fragment.activity, AddEditActivity::class.java)
            intent.putExtra(Const.KEY_LESSON_ID, lessonId)
            fragment.startActivityForResult(intent, Const.REQUEST_ADD_EDIT)
        }

        fun startPreviewActivity(activity: Activity, lessonId: Int) {
            val intent = Intent(activity, PreviewActivity::class.java)
            intent.putExtra(Const.KEY_LESSON_ID, lessonId)
            activity.startActivityForResult(intent, Const.REQUEST_ADD_EDIT)
        }

        fun startSettingsActivity(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }
}