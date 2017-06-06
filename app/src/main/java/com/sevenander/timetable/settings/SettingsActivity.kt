package com.sevenander.timetable.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sevenander.timetable.R


class SettingsActivity : AppCompatActivity(), SettingsView {

    private lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        init()
    }

    override fun showDaySettings(items: Array<String?>, checkedItems: BooleanArray) {
        val builder = AlertDialog.Builder(this@SettingsActivity)

        builder.setMultiChoiceItems(items, checkedItems, selectionChanged(checkedItems))
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.setPositiveButton(android.R.string.ok, positiveClick(checkedItems))

        builder.create().show()
    }

    override fun context(): Context {
        return this@SettingsActivity
    }

    private fun init() {
        presenter = SettingsPresenter(this@SettingsActivity)
        presenter.loadSettings()
    }

    private fun selectionChanged(checkedItems: BooleanArray):
            DialogInterface.OnMultiChoiceClickListener =
            DialogInterface.OnMultiChoiceClickListener({ dialog, which, isChecked ->
                checkedItems[which] = isChecked
            })

    private fun positiveClick(checkedItems: BooleanArray): DialogInterface.OnClickListener =
            DialogInterface.OnClickListener({ _, _ ->
                presenter.saveDays(checkedItems)
                setResult(Activity.RESULT_OK)
            })
}
