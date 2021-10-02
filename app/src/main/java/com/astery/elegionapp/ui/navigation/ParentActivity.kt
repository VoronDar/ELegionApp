package com.astery.elegionapp.ui.navigation

import android.os.Bundle

interface ParentActivity {
    fun move(action: FragmentNavController, bundle: Bundle?)
    fun setTitle(title:String?)
}