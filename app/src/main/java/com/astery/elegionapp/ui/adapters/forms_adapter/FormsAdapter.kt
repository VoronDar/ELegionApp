package com.astery.elegionapp.ui.adapters.forms_adapter

import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.astery.elegionapp.R
import com.google.android.material.textfield.TextInputEditText


class FormsAdapter(parent: Fragment) {

    val form:LinearLayout = parent.layoutInflater.inflate(R.layout.form, null) as LinearLayout
    private var branches: ArrayList<ArrayList<FormElement>> = ArrayList()
    private var brancesNames: ArrayList<String>
    private var selectedBranch:Int = 0

    init {
        branches.add(ArrayList())
        brancesNames = ArrayList()
    }

    fun addItem(element: FormElement, brancePos:Int):FormsAdapter{
        branches[brancePos].add(element)
        return this
    }
    /** add item in every branch*/
    fun addItem(element: FormElement):FormsAdapter{
        for (i in branches){
            i.add(element)
        }
        return this
    }
    /** add a spinner that can hold some other forms*/
    fun addItemForBranch(element: SpinnerElement):FormsAdapter{
        if (brancesNames.size != 0 && branches[0].size != 0) throw RuntimeException("Forms adapter unable to start second branch")
        brancesNames = element.options as ArrayList<String>
        branches.clear()
        for (i in brancesNames){
            branches.add(ArrayList())
            branches[branches.size-1].add(element)
        }
        element.listener = object:ChangeBranchListener{
            override fun change(pos: Int) {
                selectedBranch = pos
                draw(pos)
            }
        }

        return this
    }

    fun draw(br: Int){
        save(br)
        form.removeAllViews()
        for (i in branches[br]){
            form.addView(i.view)
        }
    }

    private fun save(br:Int){
        for (i in branches[br]){
            i.savedState = i.getInfo()
        }
    }

    fun getResult(  ):Map<String,String?>{
        val map: MutableMap<String, String?> = HashMap()
        for (i in branches[selectedBranch])
            map[i.name] = i.getInfo()
        return map
    }

}

/**
 * basic element for forms in alert dialogs
 * @param name is used what you can assign properties*/
abstract class FormElement(var name:String){
    lateinit var view: View
    abstract fun getInfo():String?
    var savedState:String? = null
}

class TextFormElement(text :String, parent:Fragment, name:String) :FormElement(name){
    init{
        view = parent.layoutInflater.inflate(R.layout.unit_alert_text, null)
        (view.findViewById(R.id.text) as TextView).text = text
    }

    override fun getInfo(): String? {
        return null
    }
}
open class EditTextElement(hint :String, parent:Fragment, name:String) :FormElement(name){
    init{
        view = parent.layoutInflater.inflate(R.layout.unit_alert_form, null)
        (view.findViewById(R.id.text) as EditText).hint = hint
    }

    override fun getInfo(): String? {
        return (view.findViewById(R.id.text)as TextInputEditText).text.toString()
    }
}
class LongEditTextElement(hint :String, parent:Fragment, name:String) :FormElement(name){
    init{
        view = parent.layoutInflater.inflate(R.layout.unit_alert_form, null)
        (view.findViewById(R.id.text) as TextView).hint = hint
        view.findViewById<View>(R.id.textField).minimumHeight = 100
    }

    override fun getInfo(): String? {
        return (view.findViewById(R.id.text)as TextInputEditText).text.toString()
    }
}

class NotEditableEditTextElement(hint :String, parent:Fragment, name:String) :EditTextElement(hint, parent, name){
    init{
        val text= view.findViewById<EditText>(R.id.text)
        text.setText(hint)
        text.isEnabled = false
        text.isClickable = false
        text.isFocusable = false
    }

    override fun getInfo(): String? {
        return (view.findViewById(R.id.text)as TextInputEditText).text.toString()
    }
}

class SpinnerElement(var options: List<String>, parent:Fragment, name:String) :FormElement(name){

    var listener :ChangeBranchListener? = null

    init{
        view = parent.layoutInflater.inflate(R.layout.unit_alert_form_select, null)
        view.findViewById<AutoCompleteTextView>(R.id.text).setAdapter(ArrayAdapter(
            parent.requireContext(),
            R.layout.dropdown_menu_item,
            options
        ))

        view.findViewById<AutoCompleteTextView>(R.id.text).onItemClickListener = object:AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (listener != null)
                    listener?.change(position)
            }
        }
    }

    override fun getInfo(): String? {
        return (view.findViewById(R.id.text)as AutoCompleteTextView).text.toString()
    }
}

interface ChangeBranchListener{
    fun change(pos:Int)
}