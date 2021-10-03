package com.astery.elegionapp.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.astery.elegionapp.R
import com.astery.elegionapp.pojo.User
import com.astery.elegionapp.ui.adapters.forms_adapter.ChangeBranchListener
import com.astery.elegionapp.ui.adapters.forms_adapter.FormElement
import com.astery.elegionapp.ui.adapters.forms_adapter.FormsAdapter
import com.astery.elegionapp.ui.adapters.forms_adapter.SpinnerElement
import com.astery.elegionapp.ui.adapters.units.Task
import com.astery.elegionapp.ui.views.utils.SD
import com.google.android.material.textfield.TextInputEditText


class TasksAdapter(val parent: Fragment, var form:ViewGroup) {
    private lateinit var blockListener:BlockListener
    lateinit var units:List<Task>

    fun setBlockListener(block_listener: BlockListener) {
        blockListener = block_listener
    }

    interface BlockListener {
        fun onClick(position: Int)
    }


    fun update(){
        draw(units)
    }

    fun draw(units: List<Task>) {
        this.units = units
        form.removeAllViews()

        for (i in units.indices) {
            val task = units[i]
            val view =
                parent.layoutInflater.inflate(R.layout.unit_development_task, null) as LinearLayout
            view.findViewById<TextView>(R.id.title).text = task.title
            when (task.state) {
                // TODO - make drarwables
                1 -> SD.setDrawable(
                    view.findViewById(R.id.icon),
                    R.drawable.ic_elegion,
                    parent.requireContext()
                )
                0 -> SD.setDrawable(
                    view.findViewById(R.id.icon),
                    R.drawable.ic_elegion,
                    parent.requireContext()
                )
                -1 -> SD.setDrawable(
                    view.findViewById(R.id.icon),
                    R.drawable.ic_elegion,
                    parent.requireContext()
                )

            }

            view.setOnClickListener{
                    if (this::blockListener.isInitialized){
                        blockListener.onClick(i)
                }
            }


            form.addView(view)
        }
    }

}