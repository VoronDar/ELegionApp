package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.databinding.FragmentLoginBinding
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.viewmodels.LoginViewModel
import com.astery.elegionapp.ui.views.XFragment

class LoginFragment : XFragment() {

    private lateinit var thisBinding:FragmentLoginBinding

    private val passwordKey = "password"
    private val loginKey = "key"

    private val viewModel: LoginViewModel by viewModels()

    private var manager = ForgetPasswordManager();



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentLoginBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            thisBinding.password.setText(savedInstanceState.getString(passwordKey))
            thisBinding.login.setText(savedInstanceState.getString(loginKey))
        }
    }

    override fun prepareAdapters(){
    }

    override fun setViewModelListeners() {
        viewModel.repository = (requireActivity().application as App).container.repository
    }

    override fun setListeners() {
        thisBinding.enter.setOnClickListener {
            viewModel.login(object : JobListener {
                override fun done(success: Boolean) {
                    if (success) {
                        move(FragmentNavController.SMS, null)
                    } else{
                        thisBinding.wrongData.visibility = VISIBLE
                    }
                }
            }, thisBinding.login.text.toString(), thisBinding.password.text.toString())
        }

        thisBinding.password.setOnEditorActionListener { v, actionId, event ->
            if (manager.setVisible())
                thisBinding.wrongData.visibility = VISIBLE
            else
                thisBinding.wrongData.visibility = INVISIBLE
            if (manager.state != manager.setVisible())
                manager.state = !manager.state
            true
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(passwordKey, thisBinding.password.text.toString())
        outState.putString(loginKey, thisBinding.login.text.toString())
    }

    class ForgetPasswordManager{
        var state = false
        fun setVisible(): Boolean{
            return state
        }
    }
}