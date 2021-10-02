package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.databinding.FragmentLoginBinding
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.pojo.User
import com.astery.elegionapp.repository.listeners.GetItemListener
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.viewmodels.LoginViewModel
import com.astery.elegionapp.ui.views.XFragment
import com.astery.elegionapp.ui.views.utils.BlockableBundle
import com.astery.elegionapp.ui.views.utils.VS

class LoginFragment : XFragment() {

    private lateinit var thisBinding:FragmentLoginBinding

    private val passwordKey = "password"
    private val loginKey = "key"

    private val viewModel: LoginViewModel by viewModels()

    private var manager = ForgetPasswordManager();
    private val blockManager = BlockableBundle()



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

        blockManager.addView(thisBinding.password, "all")
        blockManager.addView(thisBinding.login, "all")
        blockManager.addView(thisBinding.enter, "all")
        blockManager.addView(thisBinding.forget, "all")
        blockManager.addView(thisBinding.register, "all")
    }

    override fun prepareAdapters(){
    }

    override fun setViewModelListeners() {
        viewModel.repository = (requireActivity().application as App).container.repository
    }

    override fun setListeners() {
        thisBinding.enter.setOnClickListener {
            blockManager.lock(true, "all")
            viewModel.login(object : JobListener {
                override fun done(success: Boolean) {
                    blockManager.lock(false, "all")
                    if (success) {
                        TODO()
                        //move(FragmentNavController.SMS, null)
                    } else{
                        thisBinding.wrongData.visibility = VISIBLE
                        thisBinding.enter.visibility = VS.get(false)
                        thisBinding.forget.visibility = VS.get(true)
                    }
                }
            }, thisBinding.login.text.toString(), thisBinding.password.text.toString())
        }


        thisBinding.password.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                thisBinding.enter.visibility = VS.get(!manager.setVisible())
                thisBinding.forget.visibility = VS.get(manager.setVisible())
                if (manager.state != manager.setVisible())
                    manager.state = !manager.state
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        thisBinding.forget.setOnClickListener { TODO("Not yet implemented") }

        clickToMove(thisBinding.register, FragmentNavController.AUTH, null)
    }

    override fun onBackPressed(): Boolean {
        return false
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(passwordKey, thisBinding.password.text.toString())
        outState.putString(loginKey, thisBinding.login.text.toString())
    }

    override fun getTitle(): String? {
        return null
    }
}
class ForgetPasswordManager{
    var state = false
    fun setVisible(): Boolean{
        return state
    }
}

