package com.sevenander.timetable.viewTimetable.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.R
import java.util.*

/**
 * Created by andrii on 5/29/17.
 */
class LessonListAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<LessonEntity> = Collections.emptyList<LessonEntity>()
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemClickListener: LessonItemClickListener? = null

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_content, parent, false)
        return LessonItemViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewHolder = holder as LessonItemViewHolder
        val item = items[position]

        viewHolder.setLessonEntity(item)
        viewHolder.tvItem.text = String.format("%s. %s", item.id, item.name)
    }

    fun setItemClickListener(itemClickListener: LessonItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setListItems(listItems: List<LessonEntity>) {
        items = listItems
        notifyDataSetChanged()
    }
}