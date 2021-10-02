package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astery.elegionapp.databinding.FragmentLoginBinding
import com.astery.elegionapp.databinding.FragmentSmsBinding
import com.astery.elegionapp.ui.views.XFragment

/** */
class SmsFragment : XFragment() {

    private lateinit var thisBinding:FragmentSmsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSmsBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentSmsBinding
        return binding.root
    }




    override fun prepareAdapters(){
    }

    override fun setViewModelListeners() {
    }

    override fun setListeners() {
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun getTitle(): String? {
        return null
    }
}