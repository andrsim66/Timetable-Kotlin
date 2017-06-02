package com.sevenander.timetable.util

import android.app.Activity
import android.content.Intent
import com.sevenander.timetable.addEditLesson.AddEditActivity
import com.sevenander.timetable.previewLesson.PreviewActivity

/**
 * Created by andrii on 6/1/17.
 */
class Navigator {
    companion object {
        fun startAddActivity(activity: Activity) {
            activity.startActivity(Intent(activity, AddEditActivity::class.java))
        }

        fun startPreviewActivity(activity: Activity, lessonId: Int) {
            val intent = Intent(activity, PreviewActivity::class.java)
            intent.putExtra(Const.KEY_LESSON_ID, lessonId)
            activity.startActivity(intent)
        }
    }
}