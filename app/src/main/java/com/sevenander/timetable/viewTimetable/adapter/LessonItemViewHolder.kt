package com.sevenander.timetable.viewTimetable.adapter


import android.support.v7.widget.RecyclerView
import android.view.View
import com.sevenander.timetable.data.model.LessonEntity

/**
 * Created by andrii on 5/29/17.
 */
class LessonItemViewHolder(itemView: View,
                           private var itemClickListener: LessonItemClickListener?)
    : RecyclerView.ViewHolder(itemView) {

    private lateinit var lesson: LessonEntity

    init {
        itemView.setOnClickListener({
            itemClickListener?.onItemClick(lesson.id)
        })
    }

    fun setLessonEntity(lessonEntity: LessonEntity) {
        this.lesson = lessonEntity
    }
}