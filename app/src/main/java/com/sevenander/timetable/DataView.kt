package com.sevenander.timetable

import android.content.Context

/**
 * Created by andrii on 5/29/17.
 */
interface DataView {

    fun showProgress()

    fun hideProgress()

    fun showError(message: String)

    fun hideError()

    fun context(): Context
}