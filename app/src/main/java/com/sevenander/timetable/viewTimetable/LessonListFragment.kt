package com.sevenander.timetable.viewTimetable

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.sevenander.timetable.data.model.LessonDay
import com.sevenander.timetable.data.model.LessonEntity
import com.sevenander.timetable.util.Const
import com.sevenander.timetable.util.Navigator
import com.sevenander.timetable.viewTimetable.adapter.LessonItemClickListener
import com.sevenander.timetable.viewTimetable.adapter.LessonListAdapter
import com.sevenander.timetable.R

class LessonListFragment : Fragment(), LessonListView, LessonItemClickListener {

    @BindView(R.id.tv_lessons_message) lateinit var tvMessage: TextView
    @BindView(R.id.pb_lessons) lateinit var pbLessons: ProgressBar
    @BindView(R.id.rv_lessons) lateinit var rvLessons: RecyclerView

    private lateinit var unbinder: Unbinder

    private var lessonDay: LessonDay? = null

    private var lessonListAdapter: LessonListAdapter? = null

//    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            lessonDay = LessonDay.valueOf(arguments.getString(Const.KEY_LESSON_DAY))
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_lesson_list, container, false)
        unbinder = ButterKnife.bind(this@LessonListFragment, view)

        init()
        setupViews()
        return view
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            mListener = context as OnFragmentInteractionListener?
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun showProgress() {
        pbLessons.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbLessons.visibility = View.GONE
    }

    override fun showError(message: String) {
        tvMessage.text = message
        tvMessage.visibility = View.VISIBLE
    }

    override fun hideError() {
        tvMessage.visibility = View.GONE
    }

    override fun context(): Context {
        return activity
    }

    override fun showList(lessons: List<LessonEntity>) {
        lessonListAdapter?.setListItems(lessons)
        rvLessons.visibility = View.VISIBLE
    }

    override fun hideList() {
        rvLessons.visibility = View.GONE
    }

    override fun onItemClick(lessonId: Int) {
        Navigator.startPreviewActivity(activity, lessonId)
    }

    private fun init() {
        lessonListAdapter = LessonListAdapter(context())
        lessonListAdapter?.setItemClickListener(this@LessonListFragment)
        val presenter = LessonListPresenter(this@LessonListFragment)
        presenter.init(lessonDay!!)
    }

    private fun setupViews() {
        rvLessons.setHasFixedSize(true)
        rvLessons.itemAnimator = DefaultItemAnimator()
        rvLessons.layoutManager = LinearLayoutManager(context())
        rvLessons.adapter = lessonListAdapter
    }

    companion object {
        fun newInstance(lessonDay: String): LessonListFragment {
            val fragment = LessonListFragment()
            val args = Bundle()
            args.putString(Const.KEY_LESSON_DAY, lessonDay)
            fragment.arguments = args
            return fragment
        }
    }
}
