package com.astery.elegionapp.ui.views.utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.astery.elegionapp.R

class FormDialogue(fragment: Fragment, resource: Int, title: String) {

    val view:View
    private val alertDialog:AlertDialog

    init {
        val adb = AlertDialog.Builder(fragment.requireContext())
        view = fragment.layoutInflater.inflate(R.layout.info_card, null)
        adb.setView(view)
        alertDialog = adb.create()
        (view.findViewById(R.id.content) as ViewGroup).addView(fragment.layoutInflater.inflate(resource, null))
        (view.findViewById(R.id.title) as TextView).text = title
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }
    fun close(){
        alertDialog.cancel()
    }

}