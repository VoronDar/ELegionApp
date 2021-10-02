package com.astery.elegionapp.ui.views.utils

import android.view.View

/** lock or unlock all object with tag */
class BlockableBundle {
    private var map: MutableMap<String, ArrayList<View>> = HashMap()

    fun addView(view: View, tag:String){
        if (!map.containsKey(tag)){
            map[tag] = ArrayList();
        }
        map.getValue(tag).add(view)
    }


    fun lock(isLock: Boolean, tag:String){
        for (i in map.getValue(tag)){
            i.isClickable = !isLock
            i.isEnabled = !isLock
            i.isFocusable = !isLock
        }
    }
}