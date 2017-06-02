package com.sevenander.timetable.viewTimetable.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.R

/**
 * Created by andrii on 5/29/17.
 */
class LessonItemViewHolder(itemView: View,
                           private var itemClickListener: LessonItemClickListener?)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    @BindView(R.id.rl_item_content) lateinit var rlItem: RelativeLayout
    @BindView(R.id.tv_item_content) lateinit var tvItem: TextView

    private lateinit var lesson: LessonEntity

    init {
        ButterKnife.bind(this@LessonItemViewHolder, itemView)
        rlItem.setOnClickListener(this@LessonItemViewHolder)
    }

    override fun onClick(v: View?) {
        onItemClick()
    }

    fun setLessonEntity(lessonEntity: LessonEntity) {
        this.lesson = lessonEntity
    }

    fun onItemClick() {
        itemClickListener?.onItemClick(lesson.id)
    }
}