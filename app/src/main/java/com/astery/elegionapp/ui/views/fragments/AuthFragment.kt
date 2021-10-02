package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.databinding.FragmentAuthBinding
import com.astery.elegionapp.databinding.FragmentLoginBinding
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.viewmodels.AuthViewModel
import com.astery.elegionapp.ui.viewmodels.LoginViewModel
import com.astery.elegionapp.ui.views.XFragment
import com.astery.elegionapp.ui.views.utils.BlockableBundle
import com.astery.elegionapp.ui.views.utils.VS

class AuthFragment : XFragment() {

    private val submitPasswordKey = "submit_password"
    private val passwordKey = "password"
    private val nameKey = "name"
    private val surnameKey = "surname"
    private val phoneKey = "phone"

    private val viewModel: AuthViewModel by viewModels()
    private lateinit var thisBinding:FragmentAuthBinding

    private val blockableManager = BlockableBundle()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAuthBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentAuthBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            thisBinding.name.setText(savedInstanceState.getString(nameKey))
            thisBinding.surname.setText(savedInstanceState.getString(surnameKey))
            thisBinding.phone.setText(savedInstanceState.getString(phoneKey))
            thisBinding.password.setText(savedInstanceState.getString(passwordKey))
            thisBinding.passwordSubmit.setText(savedInstanceState.getString(submitPasswordKey))
        }

        blockableManager.addView(thisBinding.name, "all")
        blockableManager.addView(thisBinding.surname, "all")
        blockableManager.addView(thisBinding.phone, "all")
        blockableManager.addView(thisBinding.password, "all")
        blockableManager.addView(thisBinding.passwordSubmit, "all")
        blockableManager.addView(thisBinding.enter, "all")
    }

    override fun prepareAdapters(){
    }

    override fun setViewModelListeners() {
        viewModel.repository = (requireActivity().application as App).container.repository
    }

    override fun setListeners() {
        thisBinding.enter.setOnClickListener {
            blockableManager.lock(true, "all")
            viewModel.auth(object : JobListener {
                override fun done(success: Boolean) {
                    blockableManager.lock(false, "all")
                    if (success) {
                        val bundle = Bundle()
                        bundle.putString(phoneKey, thisBinding.phone.text.toString())
                        move(FragmentNavController.SMS, bundle)
                    } else{
                        thisBinding.wrongData.visibility = View.VISIBLE
                    }
                }
            }, thisBinding.name.text.toString(), thisBinding.surname.text.toString(),
                thisBinding.phone.text.toString(),
                thisBinding.password.text.toString(), thisBinding.passwordSubmit.text.toString())
        }

    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(passwordKey, thisBinding.password.text.toString())
        outState.putString(nameKey, thisBinding.name.text.toString())
        outState.putString(surnameKey, thisBinding.surname.text.toString())
        outState.putString(phoneKey, thisBinding.phone.text.toString())
        outState.putString(submitPasswordKey, thisBinding.passwordSubmit.text.toString())
    }
}