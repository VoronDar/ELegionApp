package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astery.elegionapp.databinding.FragmentAuthBinding
import com.astery.elegionapp.databinding.FragmentLoginBinding
import com.astery.elegionapp.ui.views.XFragment

class AuthFragment : XFragment() {

    private lateinit var thisBinding:FragmentAuthBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAuthBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentAuthBinding
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
}